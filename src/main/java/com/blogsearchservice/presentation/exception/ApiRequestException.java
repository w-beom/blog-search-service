package com.blogsearchservice.presentation.exception;

public class ApiRequestException extends RuntimeException{
    public ApiRequestException() {
        super();
    }

    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
