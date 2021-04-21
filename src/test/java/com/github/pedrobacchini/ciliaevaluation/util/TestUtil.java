package com.github.pedrobacchini.ciliaevaluation.util;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

public class TestUtil {

    public static void failure() { fail("failure the " + Thread.currentThread().getStackTrace()[3].getMethodName()); }

    public static <T> void buildConstraintViolationException(Set<ConstraintViolation<T>> violations) {
        if (!violations.isEmpty()) {
            StringBuilder messages = new StringBuilder();
            for (ConstraintViolation<?> violation : violations) {
                messages.append(violation.getPropertyPath().toString()).append(" ");
                messages.append(violation.getMessage()).append("\n");
            }
            throw new ConstraintViolationException(messages.toString(), violations);
        }
    }

    @SuppressWarnings("rawtypes")
    public static <T> void assertConstrainViolationEquals(ConstraintViolationException e, List<T> constraintAnnotationTypeExpected, String property) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        List<?> constraintAnnotationType = constraintViolations.stream()
                .map(constraintViolation ->
                        constraintViolation.getConstraintDescriptor().getAnnotation().annotationType())
                .collect(Collectors.toList());

        constraintAnnotationType.forEach(System.out::println);
        constraintAnnotationTypeExpected.forEach(System.out::println);

        assertThat(constraintAnnotationType.containsAll(constraintAnnotationTypeExpected)).isTrue();

        ConstraintViolationImpl violation = (ConstraintViolationImpl) constraintViolations.iterator().next();
        assertThat(property).isEqualTo(violation.getPropertyPath().toString());

        assertThat(constraintViolations.size()).isEqualTo(constraintAnnotationTypeExpected.size());
    }
}