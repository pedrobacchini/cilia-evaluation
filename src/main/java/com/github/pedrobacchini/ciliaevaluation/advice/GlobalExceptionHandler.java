package com.github.pedrobacchini.ciliaevaluation.advice;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.exception.ApiError;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final LocaleMessageSource localeMessageSource;

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("json-invalid-formatting");
        String debugMessage = ExceptionUtils.getRootCauseMessage(ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return super.handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("unsupported-method", ex.getMethod());
        String debugMessage = ExceptionUtils.getRootCauseMessage(ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("validation-error");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage);
        apiError.addBindingResult(ex.getBindingResult());
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request) {
        StringBuilder builder = new StringBuilder();
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t).append(", "));
        String expectedType = builder.substring(0, builder.length() - 2);
        String friendlyMessage = localeMessageSource.getMessage("unsupported-media-type", ex.getContentType(), expectedType);
        String debugMessage = ExceptionUtils.getRootCauseMessage(ex);
        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, friendlyMessage, debugMessage);
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }
}
