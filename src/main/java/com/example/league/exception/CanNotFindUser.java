package com.example.league.exception;

public class CanNotFindUser extends RuntimeException{
    protected CanNotFindUser(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CanNotFindUser() {
        super();
    }

    public CanNotFindUser(String message) {
        super(message);
    }

    public CanNotFindUser(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotFindUser(Throwable cause) {
        super(cause);
    }
}
