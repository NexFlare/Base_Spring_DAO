package com.nexflare.testhiber.exceptions;

public class IllegalArgumentException extends AbstractException{
    public IllegalArgumentException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

    public IllegalArgumentException(String errorMessage) {
        super(errorMessage);
    }
}
