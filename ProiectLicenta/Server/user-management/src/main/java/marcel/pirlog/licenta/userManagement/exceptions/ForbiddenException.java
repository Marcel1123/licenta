package marcel.pirlog.licenta.userManagement.exceptions;

public class ForbiddenException extends Exception {

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
