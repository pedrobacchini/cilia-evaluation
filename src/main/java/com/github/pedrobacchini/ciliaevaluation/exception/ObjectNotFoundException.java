package com.github.pedrobacchini.ciliaevaluation.exception;

import org.springframework.http.HttpStatus;

public class ObjectNotFoundException extends ApiException {

    private static final long serialVersionUID = -1772320525171757808L;

    public ObjectNotFoundException(String message) {
        super(message);
        this.status = HttpStatus.NOT_FOUND;
        this.friendlyMessageCode = "resource-not-found";
    }
}
