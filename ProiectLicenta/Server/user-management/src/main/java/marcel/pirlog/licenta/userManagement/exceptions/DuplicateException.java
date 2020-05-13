package marcel.pirlog.licenta.userManagement.exceptions;

public class DuplicateException extends Exception {
    public DuplicateException(String message){
        super(message);
    }

    public DuplicateException(String message, Throwable throwable){
        super(message, throwable);
    }
}
