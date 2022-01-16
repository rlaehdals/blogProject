package com.example.league.exception;

public class DuplicateTeamNameException extends RuntimeException {
    public DuplicateTeamNameException() {
        super();
    }

    public DuplicateTeamNameException(String message) {
        super(message);
    }

    public DuplicateTeamNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateTeamNameException(Throwable cause) {
        super(cause);
    }

    protected DuplicateTeamNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
