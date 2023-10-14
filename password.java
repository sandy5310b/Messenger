import java.io.Console;
import java.util.Arrays;
import java.util.Scanner;

public class password {
    

 public static void maiwsn(String[] args) {
     //The jar needs to be run from the terminal for console to work.
     Scanner input = new Scanner(System.in);

     Console console = System.console();
     String username = "";
     String password = "";
     if (console == null) 
     {
         System.out.print("Enter username: ");
         username = input.nextLine();
         System.out.print("Enter password: ");
         password = input.nextLine();
     }
     else 
     {
         username = console.readLine("Enter username: ");
         password = new String(console.readPassword("Enter password: "));
     }
     System.out.println("pas "+password);
     
     //I use the scanner for all other input in the code!
     //I do not know if there are any pitfalls associated with using the Scanner and console in this manner!
  }      

  public static void main(String[] args) {
		Console console = System.console() ;

		char[]  password = console.readPassword("Enter password: ");
		System.out.println("Password was: " + Arrays.toString(password));
		Arrays.fill(password,' ');
	}
}
