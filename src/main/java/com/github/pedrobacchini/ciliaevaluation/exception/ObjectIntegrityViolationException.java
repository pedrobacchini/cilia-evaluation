package com.github.pedrobacchini.ciliaevaluation.exception;

import org.springframework.http.HttpStatus;

public class ObjectIntegrityViolationException extends ApiException {

    private static final long serialVersionUID = -6262484321076285244L;

    public ObjectIntegrityViolationException(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
        this.friendlyMessageCode = "not-possible-delete-resource-has-order";
    }
}
