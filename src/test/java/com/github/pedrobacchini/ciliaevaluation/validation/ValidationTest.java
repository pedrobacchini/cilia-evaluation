package com.github.pedrobacchini.ciliaevaluation.validation;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class ValidationTest {

    private static ValidatorFactory validatorFactory;
    protected static Validator validator;

    @BeforeClass
    public static void setUp() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @AfterClass
    public static void close() { validatorFactory.close(); }
}
