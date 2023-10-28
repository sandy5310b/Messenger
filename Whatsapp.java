import java.util.*;
import java.io.*;
public class Whatsapp {
static DataManager obj;
static Message message;
String bold_blue = "\u001B[1;94m";
String boldGreenColor = "\u001B[1;32m";
String boldCyanColor = "\u001B[1;36m"; 
String resetColor = "\u001B[0m";
String bold_yellow = "\u001B[1;33m";
String bold_red = "\u001B[1;91m" ;
Whatsapp()
{
    obj = new DataManager();
    message = new Message();
    // obj.display();
}
void start()throws Exception
{
    Scanner s =new Scanner(System.in);
    System.out.println(bold_blue+"ent 1 to login"+resetColor);
    System.out.println(bold_blue+"ent 2 to signup"+resetColor);
    System.out.println(bold_blue+"ent 3 to exit"+resetColor);
    int ch;
    while(true)
    {
        try 
        {
            ch=Integer.parseInt(s.nextLine());
            if(ch>3 || ch<1)
            {
                throw new InputMismatchException();
            }
        } 
        catch (Exception  e) 
        {
            System.out.println(color.bold_red+"ent only no 1/2/3 "+color.resetColor);
            continue;
        }
        break;
    }
    
    switch(ch)
    {
        case 1:
            login();
            break;
        case 2:
            signUp();
            break;
        case 3:
            System.out.println(color.bold_red+"EXITING THE PROGRAM"+color.resetColor); 
            System.exit(1);
            break;   
    }
}
void login()throws Exception
    {
        Scanner s = new Scanner(System.in);
        String userName ,password ;
        User logged_User;
        // System.out.println(bold_blue+"ent the user name or password"+resetColor);
        // String userName=s.nextLine();
        // System.out.println(bold_blue+"ent the password"+resetColor);
        // String password = s.nextLine();
        // User logged_User = obj.isValidUser(userName, password);
        while(true)
        {
                System.out.println(bold_blue+"ent the user name or password"+resetColor);
                userName=s.nextLine();
                System.out.println(bold_blue+"ent the password"+resetColor);
                Console console = System.console();
                password = new String(console.readPassword());
                logged_User = obj.isValidUser(userName, password);
            if(logged_User != null)//change to != after testing
            {
                int ch;
                // String 
                "hsh".repeat(3);
                System.out.println(boldGreenColor+"\nScuessfully loginned\n"+resetColor);
                do
                {
                    System.out.println(bold_blue+" ".repeat(4)+"Hello "+userName+"\n"+resetColor);
                    System.out.println(bold_blue+"1 -- >                       view message"+resetColor);
                    System.out.println(bold_blue+"2 -- >                       send message"+resetColor);
                    System.out.println(bold_blue+"3 -- >                       view all users"+resetColor);
                    System.out.println(bold_blue+"4 -- >                       delete account "+resetColor);
                    System.out.println(bold_blue+"5 -- >                       Login/SIGNUP page "+resetColor);
                    System.out.print(bold_blue+"\n enter your choice :"+resetColor);
                    ch=Integer.parseInt(s.nextLine());
                    switch (ch){
                        case 1:
                            message.viewMessage(logged_User);   
                            break; 
                        case 2:
                            message.sendMessage(obj.data ,logged_User);
                            break;
                        case 3:
                            obj.viewAllUser();
                            break;
                        case 4:
                            System.out.println(color.bold_red+"Are u confirm about deleting the account ent (Y/S) "+color.resetColor);
                            char n = new Scanner(System.in).next().charAt(0);
                            if(Character.toLowerCase(n)=='y')
                            {
                            obj.deleteAccount(new User(logged_User.name,logged_User.mail_id,null, false, null));
                            System.out.println(boldGreenColor+"deleted succesfully"+resetColor);
                            }
                            start();
                            break;
                        case 5:
                            start();
                            break;
                            } 
                }while(ch!=5);
            }
            if(logged_User == null)
            {
                System.out.println("ent 1 to retry");
                System.out.println("2 to move to login/signup page");
                while(true)
                {
                    try
                    {
                        int n = Integer.parseInt(s.nextLine());
                        if(n==1)
                        {
                            break;
                        }
                        else
                        {
                            start();
                        }
                    }catch(NumberFormatException e)
                    {
                        System.out.println("error ent only no");
                    }
                    catch(Exception e)
                    {
                        System.out.println("unknow eror in retry login part");
                    }
                }
            }
        }
    }
public void signUp()throws Exception
{
    Scanner s = new Scanner(System.in);
    System.out.println(boldCyanColor+"ent the user name"+resetColor);
    System.out.print(" ".repeat(20));String userName=s.nextLine();
    System.out.println(boldCyanColor+"ent the gmail id"+resetColor);
    System.out.print(" ".repeat(20));String mailId = s.nextLine();
    Console console = System.console();
    if(!obj.checkDuplicateUsername(userName, mailId))
    {
        // System.out.println("can add the user");
        // System.out.println(color.bold_blue+"ent the passoword"+color.resetColor);
        String password=""; 
        while(true)
        {
        try{
            password = new String(console.readPassword(color.bold_blue+"Enter password: "+color.resetColor));
            if(PasswordChecker.identifyPasswordStrength(password))
            {
                break;
            }
            else 
            {
                // System.out.println(bold_red+"Password Is Not Strong\nrenter the password");
                throw new Exception();
            }
        }
        catch(Exception e)
        {
            System.out.println(bold_red+"password is not strong retry"+resetColor);
        }
        }
        obj.addUser(userName, mailId, password, false);
    }
    else
    {
        System.out.println(bold_red+"aldredy exist user or mail id "+resetColor);
    }
    start();
    // if()
}
static void booting ()throws Exception
{
    System.out.print(color.boldGreenColor+"\n\nStarting "+color.resetColor);
    for (int i = 0; i < 5; i++) {
        System.out.print(color.boldGreenColor+"."+color.resetColor);
        Thread.sleep(380);
    }System.out.println();
}
public static void main(String[] args)throws Exception {
    // booting();
    Whatsapp obj = new Whatsapp();
    System.out.println();
    obj.start();
}
} 