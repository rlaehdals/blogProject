package com.example.league.exception;

public class NotHaveAuthorityMakeLeagueException extends RuntimeException{
    public NotHaveAuthorityMakeLeagueException() {
        super();
    }

    public NotHaveAuthorityMakeLeagueException(String message) {
        super(message);
    }

    public NotHaveAuthorityMakeLeagueException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotHaveAuthorityMakeLeagueException(Throwable cause) {
        super(cause);
    }

    protected NotHaveAuthorityMakeLeagueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
