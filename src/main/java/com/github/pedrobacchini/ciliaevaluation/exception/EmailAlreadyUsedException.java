package com.github.pedrobacchini.ciliaevaluation.exception;

public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1762063872136501455L;

    public EmailAlreadyUsedException(String message) { super(message); }
}
