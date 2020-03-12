public class PasswordException extends Exception{
    public PasswordException(String message){
        super("\nInvalid password: " + message);
    }
}
