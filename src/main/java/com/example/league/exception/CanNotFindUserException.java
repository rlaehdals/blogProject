package com.example.league.exception;

public class CanNotFindUserException extends RuntimeException{
    protected CanNotFindUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CanNotFindUserException() {
        super();
    }

    public CanNotFindUserException(String message) {
        super(message);
    }

    public CanNotFindUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotFindUserException(Throwable cause) {
        super(cause);
    }
}
