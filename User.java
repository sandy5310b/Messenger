import java.util.*;
import java.io.*;
public class User {
    public String name = "";
    public String mail_id = "";
    public String password = "";
    public boolean admin = false;
    File path ;
    public User(String name , String mail_id , String password , boolean admin ,File path)
    {
        this.name = name ;
        this.mail_id = mail_id;
        this.password = password;
        this.path = path;
        this.admin = admin ;
    }    
    public static void main(String[] args) {
        User obj1 = new User("sarang", "ss@gasha", "qwertyui", false, new File("sd"));
        User obj2 = new User("ajay", "ss@gasha", "qwertyui", false, new File("sd"));
        User obj3 = new User("ajay","ss@gasha", "qwertyui", false, new File("sd"));
        ArrayList<User> arr = new ArrayList<User>();
        arr.add(obj1);
        arr.add(obj2);
        arr.add(obj3);
        arr.remove(obj3);
        System.out.println(obj2.toString());
        System.out.println(obj3.toString());
        for (User user : arr) {
            System.out.println("name "+user.name);
        }

    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null ) return false;
        User user = (User) obj;
        return this.name.equals(user.name ) && this.mail_id.equals(user.mail_id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, mail_id, password ,path ,admin);
    }
    @Override 
    public String toString() {
        return "hello fef";
     }
}
