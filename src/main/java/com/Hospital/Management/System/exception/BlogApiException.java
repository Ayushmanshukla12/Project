package com.Hospital.Management.System.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
    private HttpStatus status;
    private String errorMessage;

    public BlogApiException(String message, HttpStatus status, String errorMessage) {
        super(message);
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public BlogApiException(HttpStatus badRequest, String invalid_jwt_signature) {
        super(invalid_jwt_signature);
        status = badRequest;
        errorMessage = invalid_jwt_signature;
    }
}
