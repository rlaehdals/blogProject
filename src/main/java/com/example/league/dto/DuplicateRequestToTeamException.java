package com.example.league.dto;

public class DuplicateRequestToTeamException extends RuntimeException{
    public DuplicateRequestToTeamException() {
        super();
    }

    public DuplicateRequestToTeamException(String message) {
        super(message);
    }

    public DuplicateRequestToTeamException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateRequestToTeamException(Throwable cause) {
        super(cause);
    }

    protected DuplicateRequestToTeamException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
