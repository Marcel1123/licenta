package marcel.pirlog.licenta.userManagement.exceptions;

public class BadRequestException extends Exception {

    public BadRequestException(String message){
        super(message);
    }

    public BadRequestException(String message, Throwable throwable){
        super(message, throwable);
    }

}