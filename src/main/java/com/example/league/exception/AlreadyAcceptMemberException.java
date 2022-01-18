package com.example.league.exception;

public class AlreadyAcceptMemberException extends RuntimeException{
    public AlreadyAcceptMemberException() {
        super();
    }

    public AlreadyAcceptMemberException(String message) {
        super(message);
    }

    public AlreadyAcceptMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyAcceptMemberException(Throwable cause) {
        super(cause);
    }

    protected AlreadyAcceptMemberException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
