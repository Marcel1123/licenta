package marcel.versionmanagement.exceptions;

public class UnauthorizedException  extends Exception {
    public UnauthorizedException(String message){
        super(message);
    }

    public UnauthorizedException(String message, Throwable throwable){
        super(message, throwable);
    }
}
