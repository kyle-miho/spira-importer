package com.exception;

public class NullNameException extends Exception {
    public NullNameException() {
        super("Name must not be null.");
    }
    public NullNameException(String message) {
        super(message);
    }
}
