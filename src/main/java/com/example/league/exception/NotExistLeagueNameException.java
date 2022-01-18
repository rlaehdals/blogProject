package com.example.league.exception;

public class NotExistLeagueNameException extends RuntimeException{
    public NotExistLeagueNameException() {
        super();
    }

    public NotExistLeagueNameException(String message) {
        super(message);
    }

    public NotExistLeagueNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistLeagueNameException(Throwable cause) {
        super(cause);
    }

    protected NotExistLeagueNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
