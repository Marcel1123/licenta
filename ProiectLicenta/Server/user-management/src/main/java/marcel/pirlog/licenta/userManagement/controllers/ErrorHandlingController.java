package marcel.pirlog.licenta.userManagement.controllers;

import marcel.pirlog.licenta.userManagement.exceptions.*;
import marcel.pirlog.licenta.userManagement.models.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import javax.persistence.NoResultException;

@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorModel handleNotFoundException(NotFoundException nfe){
        return new ErrorModel(HttpStatus.NOT_FOUND.value(), nfe.getMessage());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorModel handleUnauthorizedException(UnauthorizedException ue){
        return new ErrorModel(HttpStatus.UNAUTHORIZED.value(), ue.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorModel handleBadRequestException(BadRequestException bre){
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), bre.getMessage());
    }

    @ExceptionHandler(MethodNotAllowedException.class)
    @ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorModel handleMethodNotAllowedException(MethodNotAllowedException mnae){
        return new ErrorModel(HttpStatus.METHOD_NOT_ALLOWED.value(), mnae.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ErrorModel handleForbiddenException(ForbiddenException fe){
        return new ErrorModel(HttpStatus.FORBIDDEN.value(), fe.getMessage());
    }

    @ExceptionHandler(NoResultException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorModel handlerNoResultException(NoResultException ne){
        return new ErrorModel(HttpStatus.NOT_FOUND.value(), ne.getMessage());
    }

    @ExceptionHandler(InvalidPathVariableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorModel handlerInvalidPathVariableException(InvalidPathVariableException ne){
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), ne.getMessage());
    }

    @ExceptionHandler(InvalidObjectsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorModel handlerInvalidObjectsException(InvalidObjectsException ne){
        return new ErrorModel(HttpStatus.BAD_REQUEST.value(), ne.getMessage());
    }

}