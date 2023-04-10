package com.nexflare.testhiber.exceptions;

import java.util.logging.Level;

public abstract class AbstractException extends RuntimeException{
    public AbstractException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }

    public AbstractException(String errorMessage) {
        super(errorMessage);
    }
}
