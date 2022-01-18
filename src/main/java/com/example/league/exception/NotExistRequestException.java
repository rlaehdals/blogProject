package com.example.league.exception;

public class NotExistRequestException extends RuntimeException{
    public NotExistRequestException() {
        super();
    }

    public NotExistRequestException(String message) {
        super(message);
    }

    public NotExistRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistRequestException(Throwable cause) {
        super(cause);
    }

    protected NotExistRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
