// // import java.io.BufferedWriter;
// // import java.io.FileWriter;
// // import java.io.IOException;
// // import java.time.LocalDateTime;
// // import java.time.format.DateTimeFormatter;
// // ///////////////////////////
// // // refer this while working with date tima -->https://mkyong.com/java8/java-8-difference-between-two-localdate-or-localdatetime/
// // ///////////////////////////
// // public class datee {
// //     public static void main(String[] args) {
// //         // Get the current time
// //         LocalDateTime currentTime = LocalDateTime.now();
        
// //         // Format the time as a string
// //         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
// //         String formattedTime = currentTime.format(formatter);
        
// //         // Specify the file path where you want to save the time data
// //         String filePath = "time.txt";
// //         System.out.println(formattedTime);
// //     }
// // }

// import java.time.DayOfWeek;
// import java.time.Duration;
// import java.time.LocalDateTime;

// public class datee {
//     public static void main(String[] args) {
        
//         // LocalDateTime dateTime1 = LocalDateTime.of(2023, 9, 20, 10, 30);
//         // LocalDateTime dateTime2 = LocalDateTime.of(2023, 9, 20, 12, 0);

//         // // Calculate the duration between dateTime1 and dateTime2
//         // Duration duration = Duration.between(dateTime1, dateTime2);

//         // // Define a reference LocalDateTime (e.g., dateTimeReference)
//         // System.out.println(D);

//         // // Add the duration to the reference LocalDateTime to get the result
//         // LocalDateTime resultDateTime = dateTimeReference.plus(duration);

//         // System.out.println("Original dateTime1: " + dateTime1);
//         // System.out.println("Original dateTime2: " + dateTime2);
//         // System.out.println("Time difference (Duration): " + duration);
//         // System.out.println("Resulting dateTime: " + resultDateTime);
//         // public static void main(String[] args) {
//             LocalDateTime dateTime = LocalDateTime.of(2023, 9, 20, 10, 32);
//             LocalDateTime dateTime2 = LocalDateTime.of(2023, 9, 20, 6, 31);
//             LocalDateTime kk = LocalDateTime.now();
        
//         // Get the day of the week as a name (e.g., "Monday")
//         String dayOfWeekName = dayOfWeek.toString();
//             System.out.println(kk.getHour());
//         }
    
// }

// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

// public class datee {
//     public static void main(String[] args) {
//         // Original date-time string
//         String originalDateTimeString = "2023-09-20-13";
//         DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH");
//         DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//         LocalDateTime parsedDateTime = LocalDateTime.parse(originalDateTimeString, originalFormatter);
//         String formattedDateTime = parsedDateTime.format(desiredFormatter);
//         LocalDateTime ll = LocalDateTime.parse(formattedDateTime, desiredFormatter);
//         System.out.println("Original Date-Time: " + originalDateTimeString);
//         System.out.println("Formatted Date-Time: " + formattedDateTime);

//             // Now you can store 'formattedDateTime' as needed.
//         // } catch (Exception e) {
//         //     System.err.println("Error parsing or formatting the date-time: " + e.getMessage());
//         // }
//     }
// }

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
class dataee {
    public static void main(String[] args) {
        String originalDateTimeString = "2023-09-02-18";
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-h");
        DateTimeFormatter desiredFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-HH"); // Adjust the pattern

        LocalDateTime kk = LocalDateTime.parse(originalDateTimeString,originalFormatter);
        System.out.println(kk.format(desiredFormatter));
        // System.out.println("Original Date-Time: " + originalDateTimeString);
        
        // LocalDateTime parsedDateTime = LocalDateTime.parse(originalDateTimeString, originalFormatter);
        // String formattedDateTime = parsedDateTime.format(desiredFormatter);
        // LocalDateTime ll = LocalDateTime.parse(formattedDateTime, originalFormatter);
        // System.out.println(ll.format(desiredFormatter));
        // System.out.println(ll.getHour());
        
        // System.out.println("Formatted Date-Time: " + formattedDateTime);
    }
}


class g {
    public static void main(String[] args) {
        String inputDate = "2023-10-04-14-30";
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy-hh-mm-a");
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(inputDate, originalFormatter);
            String formattedDate = parsedDate.format(targetFormatter);
            System.out.println("Original Date: " + inputDate);
            System.out.println("Formatted Date: " + formattedDate);
        } catch (Exception e) {
            System.out.println("Error parsing or formatting date: " + e.getMessage());
        }
    }
}
class PatterfnConversionExample {
    public static void main(String[] args) {
        // Input date-time in one format
        String inputDate = "2023-10-04-14-30";
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm");
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("d-MMM-yyyy-hh-mm-a");
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(inputDate, originalFormatter);
            String formattedDate = parsedDate.format(targetFormatter);
            LocalDateTime pp = LocalDateTime.parse(formattedDate, targetFormatter);
            System.out.println(pp.getHour());
            System.out.println("Original Date-Time: " + inputDate);
            System.out.println();
            System.out.println("Formatted Date-Time: " + formattedDate);
        } catch (Exception e) {
            System.out.println("Error parsing or formatting date-time: " + e.getMessage());
        }
    }
}
