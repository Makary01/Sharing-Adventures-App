package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_USERNAME_REGEX =
            Pattern.compile("[a-zA-Z0-9_-]{5,16}", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PASSWORD_REGEX =
            Pattern.compile(".{5,20}", Pattern.CASE_INSENSITIVE);


    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.matches();
    }

    public static boolean validateUserName (String username){
        Matcher matcher = VALID_USERNAME_REGEX.matcher(username);
        return matcher.matches();
    }
    public static boolean validatePassword (String password){
        Matcher matcher = VALID_PASSWORD_REGEX.matcher(password);
        return (matcher.matches());
    }
}
