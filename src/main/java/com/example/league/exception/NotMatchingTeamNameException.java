package com.example.league.exception;

public class NotMatchingTeamNameException extends RuntimeException{
    public NotMatchingTeamNameException() {
        super();
    }

    public NotMatchingTeamNameException(String message) {
        super(message);
    }

    public NotMatchingTeamNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotMatchingTeamNameException(Throwable cause) {
        super(cause);
    }

    protected NotMatchingTeamNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
