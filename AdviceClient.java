import java.io.InputStream;

import java.net.*;

import java.util.Scanner;

 

public class AdviceClient {

 

 

    public static void main(String args[]){

 

        AdviceClient as = new AdviceClient();

        as.connect();

 

    }

 

    public void connect(){

        try {

 

            Socket s = new Socket("172.24.207.224",4444);

            InputStream is = s.getInputStream();

            Scanner sc = new Scanner(is);

            System.out.println(sc.nextLine());

            sc.close();

            //while(sc.hasNextLine()

 

 

 

 

        }

        catch(Exception ee){

            System.out.println("error  "+ee);

        }

    }

}