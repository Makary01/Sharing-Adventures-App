package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class DateUtil {

    public static LocalDate stringToDate(String string){
        return LocalDate.parse(string);
    }

    public static Boolean isEarlierOrSame(LocalDate date1, LocalDate date2) {
        if (date1.getYear() < date2.getYear()) {
            return true;
        } else if (date1.getYear() == date2.getYear()) {
            if (date1.getDayOfYear() <= date2.getDayOfYear()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}
