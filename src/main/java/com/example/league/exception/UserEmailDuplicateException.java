package com.example.league.exception;

public class UserEmailDuplicateException extends RuntimeException{
    public UserEmailDuplicateException() {
        super();
    }

    public UserEmailDuplicateException(String message) {
        super(message);
    }

    public UserEmailDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserEmailDuplicateException(Throwable cause) {
        super(cause);
    }

    protected UserEmailDuplicateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
