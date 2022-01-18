package com.example.league.exception;

public class OverMaxLeagueSizeException extends RuntimeException{
    protected OverMaxLeagueSizeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public OverMaxLeagueSizeException() {
        super();
    }

    public OverMaxLeagueSizeException(String message) {
        super(message);
    }

    public OverMaxLeagueSizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverMaxLeagueSizeException(Throwable cause) {
        super(cause);
    }
}
