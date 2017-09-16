package com.sunshineapp.backend.controllers;

import com.sunshineapp.backend.exceptions.AbstractBackendException;
import com.sunshineapp.backend.exceptions.NoAccessToAppBackendException;
import com.sunshineapp.backend.exceptions.SendMomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractController {

    private static final String NO_ACCESS_TO_REMOTE_SERVICE_ERR_MSG = "This function is unavailable now. Please, try later.";

    @ExceptionHandler(AbstractBackendException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleAbstractBackendException(AbstractBackendException ex) {
        return ex.toString();
    }

    @ExceptionHandler({NoAccessToAppBackendException.class, SendMomException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ResponseEntity<String> handleNoAccessToAppBackendException() {
        return new ResponseEntity<>(NO_ACCESS_TO_REMOTE_SERVICE_ERR_MSG, HttpStatus.SERVICE_UNAVAILABLE);
    }

}
