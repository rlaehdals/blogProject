package com.example.league.exception;

public class AlreadyLeagueException extends RuntimeException{
    public AlreadyLeagueException() {
        super();
    }

    public AlreadyLeagueException(String message) {
        super(message);
    }

    public AlreadyLeagueException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyLeagueException(Throwable cause) {
        super(cause);
    }

    protected AlreadyLeagueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
