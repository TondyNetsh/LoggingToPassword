import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Password {
    private static boolean passwordExist = false;
    private static boolean upperCase = false;
    private static boolean lowerCase = false;
    private static boolean digitCheck = false;
    private static boolean oneSpecialChar = false;
    private static int counter = 0;
    private static Logger myLog = LogManager.getLogger(Password.class.getName());


    public static void passwordIsValid(String password) throws PasswordException{

        Pattern pass = Pattern.compile("<([{\\\\^\\-=$!|]})?\\*+.>");
        Matcher matcher = pass.matcher(password);

        if(password.isEmpty() || password == null){
            throw new PasswordException("password should exist.");
        }

        if(password.length() < 8){
            throw new PasswordException("password should be longer than than 8 characters");
        }
        if(password != null){
            passwordExist = true;
        }
        for(char c : password.toCharArray()){
            if(Character.isUpperCase(c)){
                upperCase = true;
            }
            if(Character.isLowerCase(c)){
                lowerCase = true;
            }
            if(Character.isDigit(c)){
                digitCheck = true;
            }
            if (password.matches(password)) {
                oneSpecialChar = true;
            }
        }

        if(upperCase == true){
            counter++;
        }
        if(lowerCase == true){
            counter++;
        }
        if(oneSpecialChar == true){
            counter++;
        }
        if(digitCheck == true){
            counter++;
        }

        if(!upperCase){
            throw new PasswordException("password should have at least one uppercase letter.");
        }
        if(!lowerCase){
            throw new PasswordException("password should have at least one lowercase letter.");
        }
        if(!digitCheck){
            throw new PasswordException("password should at least have one digit.");
        }
        if(!oneSpecialChar){
            throw new PasswordException("password should have at least one special character.");
        }
        System.out.println("Valid password");
    }

    public static boolean passwordIsOk(String password) throws PasswordException{
        boolean valid = false;

        if(counter >= 3){
            valid = true;
            System.out.print("Password is valid: " + valid);
        }
        else{
            System.out.print("Password is valid: " + valid);
        }
        return valid;
    }

    public static void main(String[] args) {

        System.out.println("=====================================================");
        System.out.println("" +
                "1. Password should not be empty.\n" +
                "2. Password should be at least 8 characters long.\n" +
                "3. Password should have at least one uppercase.\n" +
                "4. Password should have at least one lowercase.\n" +
                "5. Password should have at least one digit.\n" +
                "6. Password should have at least one char.");
        System.out.println("=====================================================");
        System.out.println("Enter your password: ");

        Scanner sc = new Scanner(System.in);
        String password = sc.nextLine();

        try {
            if(!Password.passwordIsOk(password)){
                myLog.debug("User password ok");
                Password.passwordIsValid(password);
                Password.passwordIsOk(password);
            }
            else if (Password.passwordIsOk(password)){
                myLog.error("User password not ok");
                //System.out.println("User password not okay.");
            }
        }
        catch (PasswordException e){
            myLog.error(e.getMessage());
        }
    }
}
