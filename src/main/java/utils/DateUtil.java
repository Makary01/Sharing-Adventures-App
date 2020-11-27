package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class DateUtil {
    public static String dateToString(LocalDate date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(date);
    }

    public static LocalDate stringToDate(String string){
        return LocalDate.parse(string);
    }
}
