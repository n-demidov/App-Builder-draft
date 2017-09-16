package com.sunshineapp.backend.exceptions;

import org.springframework.web.client.ResourceAccessException;

public class NoAccessToAppBackendException extends AbstractBackendException {

    public NoAccessToAppBackendException(ResourceAccessException e) {
        super(e);
    }

}
