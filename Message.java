import java.io.*;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// import
public class Message {
    public static void viewMessage(User looged_User) {
        String resetColor = "\u001B[0m";
        String boldGreenColor = "\u001B[1;32m";
        String boldCyanColor = "\u001B[1;36m";
        System.out.println(color.boldGreenColor + "reading the message" + color.resetColor);
        // System.out.println(looged_User.path.getAbsolutePath());
        try {
            ArrayList<String> str = new ArrayList<String>();
            Scanner read = new Scanner(looged_User.path);
            int count = 0;
            while (read.hasNextLine()) {
                String temp = "";
                temp = read.nextLine();
                String splitter[] = temp.split("‡");
                boolean is_read = splitter[0].equals("t");
                str.add("t" + temp.substring(1));
                String sender = splitter[1];
                String message = splitter[2];
                String time = splitter[3];
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
                DateTimeFormatter desiredFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
                LocalDateTime parsedDateTime = LocalDateTime.parse(time, format);
                String formattedDateTime = parsedDateTime.format(desiredFormat);
                // System.out.println("formetted string "+formattedDateTime);
                LocalDateTime message_sended_date = LocalDateTime.parse(formattedDateTime, desiredFormat);
                LocalDateTime ll = LocalDateTime.parse(formattedDateTime, desiredFormat);

                LocalDateTime sended_Time = LocalDateTime.parse(time, format);
                // System.out.println("recived time "+sended_Time.format(format));
                LocalDateTime currentTime = LocalDateTime.now();
                Duration duration = Duration.between(sended_Time, currentTime);
                long secondsDifference = duration.getSeconds();
                long hourDiffrence = duration.toHours();
                long daysdiffrence = duration.toDays();
                String Message_Heading = sender + " ";
                // System.out.println("second diffrence "+secondsDifference);
                // System.out.println("hours diffrence "+hourDiffrence);
                // System.out.println("get hour "+sended_Time.getHour());
                if (sended_Time.getDayOfYear() == currentTime.getDayOfYear()) {
                    if (secondsDifference < 3600) {
                        if (secondsDifference < 60) {
                            // Less than a minute ago
                            Message_Heading += "  ( " + secondsDifference + " seconds ago )";
                        } else if (secondsDifference < 3600) {
                            long minutes = secondsDifference / 60;
                            Message_Heading += "  ( " + minutes + "  minutes ago )";
                        }
                    } else if (hourDiffrence < 24) {
                        Message_Heading += "today "
                                + (sended_Time.getHour() > 12 ? (sended_Time.getHour() - 12) : sended_Time.getHour())
                                + ":" + sended_Time.getMinute()
                                + ((message_sended_date.getHour() < 13) ? " AM " : " PM ");
                    }
                } else if (sended_Time.getDayOfYear() == currentTime.getDayOfYear() - 1) {
                    Message_Heading += "yesterday "
                            + (sended_Time.getHour() > 12 ? (sended_Time.getHour() - 12) : sended_Time.getHour()) + ":"
                            + sended_Time.getMinute() + ((message_sended_date.getHour() < 13) ? " AM " : " PM ");
                } else if (daysdiffrence < 8) {
                    DayOfWeek dayOfWeek = sended_Time.getDayOfWeek();
                    String dayOfWeekName = dayOfWeek.toString();
                    Message_Heading += dayOfWeekName
                            + (sended_Time.getHour() > 12 ? (sended_Time.getHour() - 12) : sended_Time.getHour()) + ":"
                            + sended_Time.getMinute() + ((message_sended_date.getHour() < 13) ? " AM " : " PM ");
                } else {
                    Message_Heading += message_sended_date.getDayOfMonth() + " " + message_sended_date.getMonth() + " "
                            + message_sended_date.getYear() + " "
                            + (sended_Time.getHour() > 12 ? (sended_Time.getHour() - 12) : sended_Time.getHour()) + ":"
                            + sended_Time.getMinute() + ((message_sended_date.getHour() < 13) ? " AM " : " PM ");
                }
                // System.out.println(is_read?"Read ":"unRead");
                System.out.println(color.bold_blue + Message_Heading + " : " + color.resetColor);
                String message_after_edit = message_editor(message, Message_Heading.length());
                if (!is_read) {
                    count++;
                    System.out.println(boldGreenColor + message_after_edit + resetColor);
                } else {
                    System.out.println(boldCyanColor + message_after_edit + resetColor);
                }

            }
            if (count > 0)
                reWrite_Message_File(str, looged_User.path);
        } catch (Exception e) {
            System.out.println(color.bold_red + "error whiel reading the message in the logged user" + e.getMessage());
        }
    }

    static void printUserList(ArrayList<User> users) {

        for (User user : users) {
            System.out.print(color.boldCyanColor + user.name + " : " + color.resetColor);
        }
        System.out.println();
    }

    public static void sendMessage(ArrayList<User> user, User logged_User) throws Exception {
        printUserList(user);
        User reciver = null;
        System.out.println("logged user " + logged_User.name);
        System.out.println(color.boldCyanColor + "\nEnter name of receiver: " + color.resetColor);
        String recieverName = new Scanner(System.in).nextLine();
        for (User obj : user) {

            if (obj.name.equals(recieverName)) {
                converstion(logged_User, obj);
                reciver = obj ;
                break;
            }
        }
        DateTimeFormatter pp = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        System.out.println("\n\n\nThe time when wrinting the programm "+LocalDateTime.now().format(pp));
        FileWriter fr = new FileWriter(reciver.path, true);
        fr.write("f‡"+logged_User.name+'‡'+getMessage()+'‡'+LocalDateTime.now().format(pp)+"\n");
        fr.close();
        
    }
    static void message(ArrayList<User> user, User logged_User)
    {
        User reciver = null;
        try
        {

        
        printUserList(user);
        System.out.println("logged user " + logged_User.name);
        System.out.println(color.boldCyanColor + "\nEnter name of receiver: " + color.resetColor);
        String recieverName = new Scanner(System.in).nextLine();
        for (User obj : user) {

            if (obj.name.equals(recieverName)) {
                converstion(logged_User, obj);
                reciver = obj;
                break;
            }
        }
        System.out.println("1 to send message ");
        System.out.println("2 to goback");
        int choice=new Scanner(System.in).nextInt();
        if(choice==1)
        {   
            String sentence ="";
            Scanner s=new Scanner(System.in);
            System.out.print("send ");
            String str="";
            while(s.hasNextLine()&& !(str=s.nextLine()).equals("stop"))
            {
                sentence+=str+'#';
            }
            sentence=sentence.substring(0,sentence.length()-1);
            FileWriter fr = new FileWriter(reciver.path, true);
        }
        }
        catch(Exception e)
        {

        }
        
    }
    static ArrayList<String> read_Sended_Message(User sender, User reciver) {
        ArrayList<String> arr = new ArrayList<String>();
        System.out.println("sender " + sender.name + " reciver name " + reciver.name);
        // System.out.println("sender path "+sender.path+" recivers path
        // "+reciver.path);
        int i = 0, j = 0;
        try {
            Scanner reciverFile = new Scanner(reciver.path);
            Scanner senderFile = new Scanner(sender.path);
            String sender_message = null, reciver_message = null;
            while (reciverFile.hasNextLine() || senderFile.hasNextLine()) {
                if (sender_message == null) {
                    i++;
                    System.out.println("sender message is null ");
                    sender_message = null;
                    while (senderFile.hasNextLine()) {
                        System.out.println("came inside the senders file ");
                        System.out.println(sender.path);
                        String temp = senderFile.nextLine();
                        String splitter[] = temp.split("‡");
                        System.out.println("temp " + temp);
                        // System.out.println("splitter[1] "+splitter[1]+" reciver name "+reciver.name);
                        if (splitter[1].equals(reciver.name)) {
                            System.out.println("i == >" + i + " usha messsage " + splitter[1]);
                            sender_message = temp;
                            break;
                        }
                    }
                }
                if (reciver_message == null) {
                    j++;
                    System.out.println("came inside the recivers file ");
                    System.out.println("reciver file is empty");
                    while (reciverFile.hasNextLine()) {
                        String temp = reciverFile.nextLine();
                        String splitter[] = temp.split("‡");
                        if (splitter[1].equals(sender.name)) {
                            System.out.println("i == >" + i + " sarang messsage " + splitter[1]);
                            reciver_message = temp;
                            break;
                        }
                    }
                }
                System.out.println(reciver_message + "*******************" + sender_message);
                if (reciver_message != null && sender_message != null
                        && getdate(reciver_message).isBefore(getdate(sender_message))) {
                    String message[] = reciver_message.split("‡");
                    System.out.println("reciver message " + message[2]);
                    arr.add(reciver_message);
                    reciver_message = null;

                } else if (reciver_message != null && sender_message != null
                        && getdate(reciver_message).isAfter(getdate(sender_message))) {
                    String message[] = sender_message.split("‡");
                    System.out.println("sender message " + message[2]);
                    arr.add(sender_message);
                    sender_message = null;
                } else {
                    // String message1[] = sender_message.split("‡");
                    // sender_message = null;
                    // String message2[] = reciver_message.split("‡");
                    // reciver_message = null;
                    System.out.println(color.bold_red + "same time soo part 1-->" + sender_message + "part 2-->"
                            + reciver_message + color.resetColor);
                    if (sender_message == null) {
                        arr.add(reciver_message);
                    } else {
                        arr.add(sender_message);
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("error while reading the message " + e.getMessage());
        }
        System.out.println("message in order");
        for (String iterable_element : arr) {
            System.out.println(iterable_element);
        }
        return arr;
    }

    static LocalDateTime getdate(String str) {
        str = str.substring(str.lastIndexOf("‡") + 1, str.length());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        LocalDateTime date = LocalDateTime.parse(str, format);
        return date;
    }

    public static String message_editor(String str, int n) {
        String message = "";
        StringTokenizer tokenizer = new StringTokenizer(str, "#");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            message += " ".repeat(n + 4) + token + "\n";
            // System.out.println("tokens == >"+token);
        }
        return message;
    }

    // }
    static void reWrite_Message_File(ArrayList<String> s, File file) {
        System.out.println("rewrit the file ");
        try (FileWriter fr = new FileWriter(file)) {
            for (String message : s) {

                fr.write(message + "\n");
            }
            fr.close();
        } catch (Exception e) {
            System.out.println("error while re-writing the message logged user file");
        }
    }

    // void printingMessagge(ArrayList<String> arr)
    // {
    // for (String i : arr) {
    // System.out.println(color.boldGreenColor+"reading the
    // message"+color.resetColor);
    // // System.out.println(looged_User.path.getAbsolutePath());
    // try
    // {
    // int count = 0;
    // String temp = "";
    // temp = i;
    // String splitter[] = temp.split("‡");
    // boolean is_read = splitter[0].equals("t");
    // // str.add("t"+temp.substring(1));
    // String sender = splitter[1];
    // String message = splitter[2];
    // String time = splitter[3];
    // DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
    // DateTimeFormatter desiredFormat =
    // DateTimeFormatter.ofPattern("dd-MM-yyyy-HH-mm");
    // LocalDateTime parsedDateTime = LocalDateTime.parse(time, format);
    // String formattedDateTime = parsedDateTime.format(desiredFormat);
    // // System.out.println("formetted string "+formattedDateTime);
    // LocalDateTime message_sended_date =
    // LocalDateTime.parse(formattedDateTime,desiredFormat);
    // LocalDateTime ll = LocalDateTime.parse(formattedDateTime, desiredFormat);

    // LocalDateTime sended_Time = LocalDateTime.parse(time, format);
    // // System.out.println("recived time "+sended_Time.format(format));
    // LocalDateTime currentTime = LocalDateTime.now();
    // Duration duration = Duration.between(sended_Time, currentTime);
    // long secondsDifference = duration.getSeconds();
    // long hourDiffrence = duration.toHours();
    // long daysdiffrence = duration.toDays();
    // String Message_Heading = sender+" ";
    // // System.out.println("second diffrence "+secondsDifference);
    // // System.out.println("hours diffrence "+hourDiffrence);
    // // System.out.println("get hour "+sended_Time.getHour());
    // if(sended_Time.getDayOfYear()==currentTime.getDayOfYear())
    // {
    // if(secondsDifference < 3600)
    // {
    // if (secondsDifference < 60) {
    // // Less than a minute ago
    // Message_Heading +=" ( "+secondsDifference +" seconds ago )";
    // } else if (secondsDifference < 3600) {
    // long minutes = secondsDifference / 60;
    // Message_Heading += " ( "+minutes + " minutes ago )";
    // }
    // }
    // else if(hourDiffrence<24)
    // {
    // Message_Heading += "today
    // "+(sended_Time.getHour()>12?(sended_Time.getHour()-12):sended_Time.getHour())+":"+sended_Time.getMinute()+((message_sended_date.getHour()<13)?"
    // AM ":" PM ");
    // }
    // }
    // else if(sended_Time.getDayOfYear()==currentTime.getDayOfYear()-1)
    // {
    // Message_Heading +="yesterday
    // "+(sended_Time.getHour()>12?(sended_Time.getHour()-12):sended_Time.getHour())+":"+sended_Time.getMinute()+((message_sended_date.getHour()<13)?"
    // AM ":" PM ");
    // }
    // else if(daysdiffrence<8)
    // {
    // DayOfWeek dayOfWeek = sended_Time.getDayOfWeek();
    // String dayOfWeekName = dayOfWeek.toString();
    // Message_Heading +=
    // dayOfWeekName+(sended_Time.getHour()>12?(sended_Time.getHour()-12):sended_Time.getHour())+":"+sended_Time.getMinute()+((message_sended_date.getHour()<13)?"
    // AM ":" PM ");
    // }
    // else
    // {
    // Message_Heading += message_sended_date.getDayOfMonth()+"
    // "+message_sended_date.getMonth()+" "+message_sended_date.getYear()+"
    // "+(sended_Time.getHour()>12?(sended_Time.getHour()-12):sended_Time.getHour())+":"+sended_Time.getMinute()+((message_sended_date.getHour()<13)?"
    // AM ":" PM ");
    // }
    // // System.out.println(is_read?"Read ":"unRead");
    // System.out.println(color.bold_blue+Message_Heading+" : "+color.resetColor);
    // String message_after_edit = message_editor(message
    // ,Message_Heading.length());
    // if(!is_read)
    // {
    // count ++;
    // System.out.println(color.boldGreenColor+message_after_edit+color.resetColor);
    // }
    // else
    // {
    // System.out.println(color.boldCyanColor+message_after_edit+color.resetColor);
    // }

    // }
    // if(count>0)
    // reWrite_Message_File(str , looged_User.path);
    // }catch(Exception e)
    // {
    // System.out.println(color.bold_red+"error whiel reading the message in the
    // logged user"+e.getMessage());
    // }
    // }
    // }
    // }

    public static ArrayList<String> getMessage(User sender, User reciver) {
        ArrayList<String> sendermessagelist = new ArrayList<String>();
        ArrayList<String> message = new ArrayList<String>();
        try {
            Scanner senderFile = new Scanner(sender.path);
            Scanner reciverFile = new Scanner(reciver.path);
            String senderMessage = null, reciverMessage = null;
            while (senderFile.hasNextLine() || reciverFile.hasNextLine() || senderMessage != null || reciverMessage != null) {
                while (senderMessage == null && senderFile.hasNextLine()) {
                    String temp = senderFile.nextLine();
                    System.out.println("temp    = > "+temp);
                    
                    if (temp.split("‡")[1].equals(reciver.name)) {
                        senderMessage = temp;
                        System.out.println("condition "+senderMessage.split("‡")[1].equals(reciver.name));
                        System.out.println(" conditioned checked message "+senderMessage);
                        sendermessagelist.add('t'+temp.substring(1));//'t means message has been read '
                        break;
                    }
                    else
                    {
                        sendermessagelist.add(temp);
                    }
                }
                System.out.println("senders     message  = "+senderMessage);
                while (reciverMessage == null && reciverFile.hasNextLine()) {   
                    String temp = reciverFile.nextLine();
                    // reciverMessage = 
                    System.out.println("reciver message == "+temp);
                    if (temp.split("‡")[1].equals(sender.name)) {
                        reciverMessage = temp;
                        break;
                    }
                }
                System.out.println("sende message == "+senderMessage +"   reciver message "+reciverMessage);
                if (reciverMessage != null && senderMessage != null&& getdate(reciverMessage).isBefore(getdate(senderMessage))) {
                    String splitter[] = reciverMessage.split("‡");
                    System.out.println("reciver message " + splitter[2]+" is printed");
                    message.add(reciverMessage);
                    reciverMessage = null;
                } else if (reciverMessage != null && senderMessage != null
                        && getdate(reciverMessage).isAfter(getdate(senderMessage))) {
                    String splitter[] = senderMessage.split("‡");
                    System.out.println("sender message " + splitter[2]+" is printed");
                    message.add(senderMessage);
                    senderMessage = null;
                } else {
                    System.out.println(color.bold_red + "same time soo part 1-->" + senderMessage + "part 2-->"
                            + reciverMessage + color.resetColor);
                    if(senderMessage == null && reciverMessage == null )
                    {
                     break;   
                    }        
                    else if (senderMessage == null) {
                        System.out.println("reciver message " + reciverMessage);
                        message.add(reciverMessage);
                        reciverMessage = null;
                    } 
                    else{
                        System.out.println("sender message " + reciverMessage);
                        message.add(senderMessage); 
                        senderMessage = null;
                    }
                }
                System.out.println("one wile is completed");

            }
        } catch (Exception e) {
            System.out.println("eroor while getting the message in the the get conversation block"+e.getMessage());
        }
        System.out.println("sender message rewriiten");
        for (String str : sendermessagelist) {
            System.out.println(str);
        }
        System.out.println("order in which the message to wriiten");
        for (String str : message) {
            System.out.println(str);
        }
        reWrite_Message_File(sendermessagelist,sender.path);
        return message;
    }

    public static void converstion(User sender, User reciver) {
        ArrayList<String> arr = getMessage(sender, reciver);
        int space = 118;
        for (String str : arr) {
            String temp = str;
            String splitter[] = temp.split("‡");
            String heading = timing(splitter[3]);
            if (splitter[1].equals(sender.name)) 
            {
                System.out.println(splitter[1]+" "+heading);
                StringTokenizer kk = new StringTokenizer(splitter[2],"‡");
                while(kk.hasMoreTokens())
                {
                    System.out.println(kk.nextToken()+" ");
                }
            }
            else
            {
                String pp = splitter[1]+" "+heading;
                System.out.println(" ".repeat(space-pp.length())+pp);
                StringTokenizer kk = new StringTokenizer(splitter[2],"‡");
                while(kk.hasMoreTokens())
                {
                    String ll= kk.nextToken();
                    System.out.println(" ".repeat(space-ll.length())+ll);
                }
            }
        }
    }

    public static String timing(String message) {

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        LocalDateTime sended_Time = LocalDateTime.parse(message, format);
        Duration duration = Duration.between(sended_Time, currentTime);
        String Message_Heading = "";
        long secondsDifference = duration.getSeconds();
        long hourDifference = duration.toHours();
        long daysDifference = duration.toDays();
        if(sended_Time.getDayOfYear()==currentTime.getDayOfYear())
        {
        if(secondsDifference < 3600)
        {
        if (secondsDifference < 60) {
        // Less than a minute ago
        Message_Heading +=" ( "+secondsDifference +" seconds ago )";
        } else if (secondsDifference < 3600) {
        long minutes = secondsDifference / 60;
        Message_Heading += " ( "+minutes + " minutes ago )";
        }
        }
        else if(hourDifference<24)
        {
        Message_Heading += "today"+(sended_Time.getHour()>12?(sended_Time.getHour()-12):sended_Time.getHour())+":"+sended_Time.getMinute()+((sended_Time.getHour()<13)?"AM ":" PM ");
        }
        }
        else if(sended_Time.getDayOfYear()==currentTime.getDayOfYear()-1)
        {
        Message_Heading +="yesterday"+(sended_Time.getHour()>12?(sended_Time.getHour()-12):sended_Time.getHour())+":"+sended_Time.getMinute()+((sended_Time.getHour()<13)?"AM ":" PM ");
        }
        else if(daysDifference<8)
        {
        DayOfWeek dayOfWeek = sended_Time.getDayOfWeek();
        String dayOfWeekName = dayOfWeek.toString();
        dayOfWeekName = dayOfWeekName.charAt(0)+dayOfWeekName.substring(1).toLowerCase();
        Message_Heading +=dayOfWeekName+" "+(sended_Time.getHour()>12?(sended_Time.getHour()-12):sended_Time.getHour())+":"+sended_Time.getMinute()+((sended_Time.getHour()<13)?"AM ":" PM ");
        }
        else
        {
        Message_Heading += sended_Time.getDayOfMonth()+" "+sended_Time.getMonth()+" "+sended_Time.getYear()+" "+(sended_Time.getHour()>12?(sended_Time.getHour()-12):sended_Time.getHour())+":"+sended_Time.getMinute()+((sended_Time.getHour()<13)?"AM ":" PM ");
        }
        return Message_Heading+"\n";
    }
    public static String getMessage()
    {
        Scanner s=new Scanner(System.in);
        System.out.print(" enter the string ");
        String str="",sentence="";
        while(s.hasNextLine()&& !(str=s.nextLine()).equals("stop"))
        {
            sentence+=str+'#';
        }
        sentence=sentence.substring(0,sentence.length()-1);
        System.out.println("sentence =="+sentence);
        return sentence;
    }
    public static void main(String[] args) {

    }
}
