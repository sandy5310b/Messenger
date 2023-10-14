import java.util.*;
import java.io.*;
public class DataManager {
    ArrayList<User> data = new ArrayList<User>();
    
    int longest_name_length = 0;
    File Database = new File("/home/sarangsu/java/whatsapp/database");
    File user_list = new File(Database, "userlist.txt");
    DataManager()
    {
        System.out.println("this is from constructor");
        try
        {
            // System.out.println("befff");
            Scanner s =new Scanner(user_list);
            while(s.hasNextLine())
            {
                // System.out.println("");
                String temp = s.nextLine();
                // System.out.println(temp);
                temp = Encryption.decrypt(temp);
                // System.out.println(temp);
                String splitter[] = temp.split("‡");
                data.add(new User(splitter[0], splitter[1], splitter[2] ,splitter[3].equals("t"),new File(Database,splitter[0]+".txt")));
                longest_name_length = Math.max(longest_name_length,splitter[0].length());
            }
        }catch(Exception e)
        {
            System.out.println(color.bold_red+"user lsit file not found"+color.resetColor);
        }
        // System.out.println("All user list ");
        // for (User i : data) 
        // {
        //     System.out.println("Name : "+i.name +"  E-mail-id : "+i.mail_id+"  Is a Admin = "+i.admin);    
        // }
    }
    User isValidUser(String name , String password)
    {
        for(User user : data)
        {
            if(user.name.equals(name))
            {   return user;
                // if(user.password.equals(password))
                // {
                //     return user;
                // } 
                // System.out.println(color.bold_red+"User name is correct but password is incorrect"+color.resetColor);
                // return null;
            }
        }
        System.out.println(color.bold_red+"User Does Not Exist"+color.resetColor);
        return null;
    }
    boolean checkDuplicateUsername(String userName ,String mail_id)
    {
        for (User user : data) {
            if(user.name.equals(userName) || user.mail_id.equals(mail_id))
            {
                if(user.name.equals(userName))
                {
                    System.out.println(color.bold_red+"user Name Alredy Exist"+color.resetColor);
                }
                else
                {
                    System.out.println(color.bold_red+"Email id Already Exist"+color.resetColor);
                }
                return true;
            }
        }
        return false;
    }
    void addUser(String userName , String mail_id , String password , boolean isAdmin)
    {
        File user_file = new File(Database , userName+".txt");
        try {
            FileWriter fr = new FileWriter(user_list , true);
            fr.write(Encryption.encrypt(userName+"‡"+mail_id+"‡"+password+"‡"+(isAdmin?"t":"f"))+"\n");
            fr.close();
            user_file.createNewFile();
            data.add(new User(userName, mail_id, password, isAdmin ,user_file));
            System.out.println(color.boldGreenColor+"succesfully added the user"+color.resetColor);
        } catch (Exception e) {
            System.out.println(color.bold_red+"Error while writing the file \"The File cannot be written \" "+color.resetColor);
        }
    }
    void display()throws Exception
    {
        System.out.println("display ll");
        for (User user : data) {
            System.out.println(user.name);
            throw new Exception();
        }
        System.out.println("disdds");
    }
    public void deleteAccount(User loggedUser)
    {
        System.out.println(color.bold_red+"deleting the account "+loggedUser.name+color.resetColor);
        try
        {
        data.remove(loggedUser);
        // System.out.println("after removing the names in the list ");
        for (User user : data) {
            // System.out.println("user name "+user.name);            
        }
        loggedUser.path = new File(Database, loggedUser.name+".txt");
        
        if(loggedUser.path.delete())
        {
            // System.out.println("File succesfully deleted");
            user_List_Delete_User(loggedUser);
        }
        else
        {
            throw new Exception();
        }
        }
        catch(Exception e)
        {
            System.out.println(color.bold_red+"Error while delting the account"+color.resetColor);
        }
    } 
    public void viewAllUser()
    {
        String bold_white = "\u001B[1;37m";
        String resetColor = "\u001B[0m";
        String bold_Yellow = "\u001B[1;33m";
        System.out.println("\n".repeat(1));
        for (User user : data) 
        {
            System.out.println( " ".repeat(20)+bold_Yellow+"User Name : "+resetColor+bold_white+user.name+resetColor+ bold_Yellow+" ".repeat(longest_name_length-user.name.length())+"  Mail - id "+resetColor+bold_white+user.mail_id+resetColor);
        }
        System.out.println("\n".repeat(1));
    }
    public void user_List_Delete_User(User loggedUser)
    {
        // System.out.println("deleting the user from the user list");
        try {
            FileWriter fr = new FileWriter(user_list);
            for (User user : data) {
                if(!loggedUser.name.equals(user.name))
                {
                    // System.out.println("dont delete "+user.name);
                    String temp = user.name+"‡"+user.mail_id+"‡"+user.password+"‡"+user.admin;
                    fr.write(Encryption.encrypt(temp)+"\n");
                }
            }
            fr.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(color.bold_red+"fiel not found "+color.resetColor);
        }
        
    }
    public static void main(String[] args) {
        // DataManager obj =new DataManager();
        // System.out.println("this is from datamanager main function");
        // obj.display();
    }
}
