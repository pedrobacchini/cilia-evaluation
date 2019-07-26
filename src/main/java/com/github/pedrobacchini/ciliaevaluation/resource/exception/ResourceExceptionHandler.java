package com.github.pedrobacchini.ciliaevaluation.resource.exception;

import com.github.pedrobacchini.ciliaevaluation.config.CustomMessageSource;
import com.github.pedrobacchini.ciliaevaluation.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.constraints.NotNull;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private final CustomMessageSource customMessageSource;

    public ResourceExceptionHandler(CustomMessageSource customMessageSource) {
        this.customMessageSource = customMessageSource;
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {
        String friendlyMessage = customMessageSource.getMessage("json-invalid-formatting");
        String debugMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return super.handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    @NotNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String friendlyMessage = customMessageSource.getMessage("validation-error");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage);
        apiError.addBindingResult(ex.getBindingResult());
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(RuntimeException ex, WebRequest request) {
        String friendlyMessage = customMessageSource.getMessage("illegal-argument");
        String debugMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return super.handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    public ResponseEntity<Object> handleObjectNotFoundException(ObjectNotFoundException ex, WebRequest request) {
        String friendlyMessage = customMessageSource.getMessage("resource-not-found");
        String debugMessage = ex.toString();
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, friendlyMessage, debugMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ObjectAlreadyExistException.class})
    public ResponseEntity<Object> handleObjectAlreadyExistException(ObjectAlreadyExistException ex, WebRequest request) {
        String friendlyMessage = customMessageSource.getMessage("resource-already-exist");
        String debugMessage = ex.toString();
        ApiError apiError = new ApiError(HttpStatus.CONFLICT, friendlyMessage, debugMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }
}
