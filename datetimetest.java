// public class datetimetest {
    
// }
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
class PatternConversionExample {
    public static void main(String[] args) {
        // Input date in one format
        String inputDateStr = "2023-10-04";
        
        // Define the original pattern and the target pattern
        DateTimeFormatter originalFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter targetFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Change to your desired target pattern
        
        try {
            // Parse the input date using the original pattern
            LocalDate parsedDate = LocalDate.parse(inputDateStr, originalFormatter);
            
            // Format the parsed date using the target pattern
            String formattedDate = parsedDate.format(targetFormatter);
            
            System.out.println("Original Date: " + inputDateStr);
            System.out.println("Formatted Date: " + formattedDate);
        } catch (Exception e) {
            System.out.println("Error parsing or formatting date: " + e.getMessage());
        }
    }
}
