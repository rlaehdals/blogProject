package com.example.league.exception;

public class UserEmailOrPasswordWrongException extends RuntimeException{
    public UserEmailOrPasswordWrongException() {
        super();
    }

    public UserEmailOrPasswordWrongException(String message) {
        super(message);
    }

    public UserEmailOrPasswordWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailOrPasswordWrongException(Throwable cause) {
        super(cause);
    }

    protected UserEmailOrPasswordWrongException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
