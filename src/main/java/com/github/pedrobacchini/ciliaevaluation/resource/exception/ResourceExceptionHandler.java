package com.github.pedrobacchini.ciliaevaluation.resource.exception;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.service.exception.ObjectNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private final LocaleMessageSource localeMessageSource;

    public ResourceExceptionHandler(LocaleMessageSource localeMessageSource) {
        this.localeMessageSource = localeMessageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("json-invalid-formatting");
        String debugMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return super.handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("unsupported-method", ex.getMethod());
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, ex.getMessage());
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
        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, friendlyMessage, ex.getMessage());
        return super.handleExceptionInternal(ex, apiError, headers, status, request);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(RuntimeException ex, WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("illegal-argument");
        String debugMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return super.handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException ex, WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("resource-not-found");
        String debugMessage = ex.toString();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, friendlyMessage, debugMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({EmailAlreadyUsedException.class})
    public ResponseEntity<Object> handleObjectAlreadyExistException(EmailAlreadyUsedException ex, WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("email-already-used");
        String debugMessage = ex.toString();
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, friendlyMessage, debugMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("operation-not-allowed");
        String debugMessage = ExceptionUtils.getRootCauseMessage(ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
