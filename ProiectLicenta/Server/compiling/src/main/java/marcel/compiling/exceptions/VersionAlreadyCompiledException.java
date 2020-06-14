package marcel.compiling.exceptions;

public class VersionAlreadyCompiledException   extends Exception {
    public VersionAlreadyCompiledException(String message){
        super(message);
    }

    public VersionAlreadyCompiledException(String message, Throwable throwable){
        super(message, throwable);
    }
}