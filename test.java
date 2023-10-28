// // public class test {
// //     public static void main(String[] args) {
// //         // ANSI escape code for green text
// //         String greenColor = "\u001B[32m";
        
// //         // ANSI escape code to reset text color to default
// //         String resetColor = "\u001B[0m";
        
// //         // Text to display in green
// //         String text = "This is green text!";
        
// //         // Print green text
// //         System.out.println(greenColor + text + resetColor);
// //     }
// // }
// public class tedst {
//     public static void main(String[] args) {
//         // ANSI escape code for bold green text
//         String boldGreenColor = "\u001B[1;32m";
        
//         // ANSI escape code to reset text color to default
//         String resetColor = "\u001B[0m";
        
//         // Text to display in bold green
//         String text = "This is bold green text!";
        
//         // Print bold green text
//         System.out.println(boldGreenColor + text + resetColor);
//     }
// }
// public class test {
//     public static void main(String[] args) {
//         String resetColor = "\u001B[0m";  // Reset text style to default
        
//         // Bold foreground (text) colors
//         String[] boldForegroundColors = {
//             "\u001B[1;30m", // Bold Black
//             "\u001B[1;31m", // Bold Red
//             "\u001B[1;32m", // Bold Green
//             "\u001B[1;33m", // Bold Yellow
//             "\u001B[1;34m", // Bold Blue
//             "\u001B[1;35m", // Bold Magenta
//             "\u001B[1;36m", // Bold Cyan
//             "\u001B[1;37m"  // Bold White
//         };
        
//         // Text to display
//         String text = "Text in bold ";
        
//         // Loop through bold foreground colors
//         for (String boldFgColor : boldForegroundColors) {
//             // Combine bold foreground color with text
//             String coloredText = boldFgColor + text + boldFgColor + "color" + resetColor;
//             System.out.println(coloredText);
//         }
//     }
// }

// class BoldCyanTextExample {
//     public static void main(String[] args) {
//         String resetColor = "\u001B[0m";  // Reset text style to default
//         String boldCyanColor = "\u001B[1;36m";  // Bold Cyan
        
//         String text = "This is bold cyan text!";
        
//         // Print text in bold cyan color
//         System.out.println(boldCyanColor + text + resetColor);
//     }
// }
// class dummy
// {
//     public static void main(String[] args) {
//         if(2<0 && Integer.parseInt("r")>2)
//         {
//             System.out.println("jdisj");
//         }
//         else{
//             System.out.println("sldf");
//         }
//     }
// }
//  class UnicodeExample {
//     public static void main(String[] args) {
//         int i=1;
//         while(i<121)
//         {
//             System.out.print("2");
//             i++;
//         }
//         System.out.println();
//     }
// }
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

 class DayOfWeekExample {
    
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/E"); // "E" represents the short day of the week format
        String dayOfWeek = now.format(formatter);
        System.out.println(now.getDayOfWeek());
        System.out.println(dayOfWeek);
    }
}
class Employee   
{  
private int regno;  
private String name;  
//constructor of Employee class  
public Employee(int regno, String name)   
{  
this.name = name;  
this.regno = regno;  
}  
public int getRegno()   
{  
return regno;  
}  
public void setRegno(int Regno)   
{  
this.regno = regno;  
}  
public String getName()   
{  
return name;  
}  
public void setName(String name)   
{  
this.name = name;  
}  
}  
 class HashcodeExample  
{  
public static void main(String[] args)   
{  
//creating two instances of the Employee class  
Employee emp1 = new Employee(918, "Maria");  
Employee emp2 = new Employee(918, "Maria");  
//invoking hashCode() method  
int a=emp1.hashCode();  
int b=emp2.hashCode();  
System.out.println("hashcode of emp1 = " + a);  
System.out.println("hashcode of emp2 = " + b);  
System.out.println("Comparing objects emp1 and emp2 = " + emp1.equals(emp2));}}