package com.blogsearchservice.presentation.exception;

import org.springframework.http.HttpStatus;

public class ErrorReponse {
    private final HttpStatus httpStatus;
    private final String errorMessage;

    public ErrorReponse(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
