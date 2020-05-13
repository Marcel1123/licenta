package marcel.pirlog.licenta.userManagement.exceptions;

public class ObjectNotFoundException extends Exception {
    public ObjectNotFoundException(String message){
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
