package com.coditas.reposervice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RepositoryNotFoundException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;

    public RepositoryNotFoundException() {
    }

    public RepositoryNotFoundException(HttpStatus status, String errorMessage) {
        super(errorMessage);
        this.httpStatus = status;
        this.message = errorMessage;

    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
