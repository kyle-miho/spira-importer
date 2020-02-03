package com.exception;

public class InvalidProjectIdException extends Exception {
    public InvalidProjectIdException() {
        super("ProjectId must be valid.");
    }
    public InvalidProjectIdException(String message) {
        super(message);
    }
}
