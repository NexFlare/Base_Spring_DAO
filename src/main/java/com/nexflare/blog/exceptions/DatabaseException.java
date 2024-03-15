package com.nexflare.blog.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseException extends AbstractException{

    private final Logger log = Logger.getAnonymousLogger();
    public DatabaseException(String errorMessage, Throwable error) {
        super(errorMessage, error);
        log.log(Level.SEVERE, errorMessage);
    }

    public DatabaseException(String errorMessage) {
        super(errorMessage);
        log.log(Level.SEVERE, errorMessage);
    }

}