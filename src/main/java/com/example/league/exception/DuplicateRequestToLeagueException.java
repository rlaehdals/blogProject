package com.example.league.exception;

public class DuplicateRequestToLeagueException extends RuntimeException{
    public DuplicateRequestToLeagueException() {
        super();
    }

    public DuplicateRequestToLeagueException(String message) {
        super(message);
    }

    public DuplicateRequestToLeagueException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRequestToLeagueException(Throwable cause) {
        super(cause);
    }

    protected DuplicateRequestToLeagueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
