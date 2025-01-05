package com.example.inventory.exception;

import org.springframework.http.HttpStatus;

public class TechnicalException extends BaseException {

    public TechnicalException(HttpStatus httpStatus, String errorCode, String errorMessage) {
        super(httpStatus, errorCode, errorMessage);
    }
}
