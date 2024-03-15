package com.nexflare.blog.exceptions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataNotFoundException extends AbstractException{

    private final Logger log = Logger.getAnonymousLogger();
    public DataNotFoundException(String errorMessage, Throwable error) {
        super(errorMessage, error);
        log.log(Level.SEVERE, "Data not found");
    }

    public DataNotFoundException(String errorMessage) {
        super(errorMessage);
        log.log(Level.SEVERE, "Data not found");
    }

}
