package marcel.compiling.exceptions;

public class InvalidObjectsException extends Exception {

    public InvalidObjectsException(String message) {
        super(message);
    }

    public InvalidObjectsException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
