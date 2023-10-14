import java.time.LocalDateTime;
import java.util.*;
public class DateMessageCollection {
    String message = "";
    LocalDateTime date ;
    DateMessageCollection(String message ,LocalDateTime date)
    {
        System.out.println("entered the DateMessageCollection class ");
        this.message = message;
        this.date = date ;
    }
}
