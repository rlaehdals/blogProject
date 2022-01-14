package com.example.league.exception;

public class AlreadyTeamException extends RuntimeException{
    public AlreadyTeamException() {
        super();
    }

    public AlreadyTeamException(String message) {
        super(message);
    }

    public AlreadyTeamException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyTeamException(Throwable cause) {
        super(cause);
    }

    protected AlreadyTeamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
