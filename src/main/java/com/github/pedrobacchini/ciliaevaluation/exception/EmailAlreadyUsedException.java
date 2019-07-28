package com.github.pedrobacchini.ciliaevaluation.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyUsedException extends ApiException {

    private static final long serialVersionUID = 1762063872136501455L;

    public EmailAlreadyUsedException(String message) {
        super(message);
        this.status = HttpStatus.CONFLICT;
    }
}
