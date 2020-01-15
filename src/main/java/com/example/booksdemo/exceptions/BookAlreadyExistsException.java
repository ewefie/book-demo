package com.example.booksdemo.exceptions;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException() {
        super();
    }

    public BookAlreadyExistsException(String message) {
        super(message);
    }

    public BookAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected BookAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
