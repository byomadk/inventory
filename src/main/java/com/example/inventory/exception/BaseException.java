package com.example.inventory.exception;

import org.springframework.http.HttpStatus;

public class BaseException extends RuntimeException {

    protected HttpStatus httpStatus;
    protected String errorCode;
    protected String errorMessage;

    public BaseException(HttpStatus httpStatus, String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
