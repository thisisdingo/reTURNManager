package turnmanager.michil.ru.Exceptions;

public class AuthException extends Exception {

    @Override
    public String getMessage() {
        return "Your secret key is not valid";
    }
}
