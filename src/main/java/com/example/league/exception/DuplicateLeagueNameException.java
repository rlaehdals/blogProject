package com.example.league.exception;

public class DuplicateLeagueNameException extends RuntimeException {
    public DuplicateLeagueNameException() {
        super();
    }

    public DuplicateLeagueNameException(String message) {
        super(message);
    }

    public DuplicateLeagueNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateLeagueNameException(Throwable cause) {
        super(cause);
    }

    protected DuplicateLeagueNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
