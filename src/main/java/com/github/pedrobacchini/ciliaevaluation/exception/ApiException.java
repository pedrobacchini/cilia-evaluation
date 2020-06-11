package com.github.pedrobacchini.ciliaevaluation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class ApiException extends RuntimeException {

    private static final long serialVersionUID = -3918161160776205983L;

    protected HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    protected String friendlyMessageCode = "no-friendly-message";

    ApiException(String message) { super(message); }
}
