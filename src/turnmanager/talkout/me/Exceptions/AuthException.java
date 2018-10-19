package turnmanager.talkout.me.Exceptions;

public class AuthException extends Exception {

    @Override
    public String getMessage() {
        return "Your secret key is not valid";
    }
}
