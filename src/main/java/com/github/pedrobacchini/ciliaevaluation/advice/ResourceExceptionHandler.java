package com.github.pedrobacchini.ciliaevaluation.advice;

import com.github.pedrobacchini.ciliaevaluation.config.LocaleMessageSource;
import com.github.pedrobacchini.ciliaevaluation.exception.ApiError;
import com.github.pedrobacchini.ciliaevaluation.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    private final LocaleMessageSource localeMessageSource;

    @ExceptionHandler({IllegalArgumentException.class})
    protected ResponseEntity<Object> handleIllegalArgumentException(RuntimeException ex, WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("illegal-argument");
        String debugMessage = ExceptionUtils.getRootCauseMessage(ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return super.handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage("operation-not-allowed");
        String debugMessage = ExceptionUtils.getRootCauseMessage(ex);
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, friendlyMessage, debugMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request) {
        String friendlyMessage = localeMessageSource.getMessage(ex.getFriendlyMessageCode());
        String debugMessage = ExceptionUtils.getRootCauseMessage(ex);
        ApiError apiError = new ApiError(ex.getStatus(), friendlyMessage, debugMessage);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), ex.getStatus(), request);
    }
}
