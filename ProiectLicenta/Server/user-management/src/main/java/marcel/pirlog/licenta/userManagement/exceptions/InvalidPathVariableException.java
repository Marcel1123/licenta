package marcel.pirlog.licenta.userManagement.exceptions;

public class InvalidPathVariableException extends Exception{

    public InvalidPathVariableException(String message){
        super(message);
    }

    public InvalidPathVariableException(String message, Throwable throwable){
        super(message, throwable);
    }
}
