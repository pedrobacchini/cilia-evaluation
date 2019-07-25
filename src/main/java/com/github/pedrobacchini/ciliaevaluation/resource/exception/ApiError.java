package com.github.pedrobacchini.ciliaevaluation.resource.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@ToString
class ApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String friendlyMessage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String debugMessage;

    private ApiError() { this.timestamp = LocalDateTime.now(); }

    ApiError(HttpStatus status, String friendlyMessage, String debugMessage) {
        this();
        this.status = status;
        this.friendlyMessage = friendlyMessage;
        this.debugMessage = debugMessage;
    }
}

