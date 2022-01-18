package com.example.league.exception;

public class AlreadyAcceptTeamException extends RuntimeException{
    public AlreadyAcceptTeamException() {
        super();
    }

    public AlreadyAcceptTeamException(String message) {
        super(message);
    }

    public AlreadyAcceptTeamException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyAcceptTeamException(Throwable cause) {
        super(cause);
    }

    protected AlreadyAcceptTeamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
