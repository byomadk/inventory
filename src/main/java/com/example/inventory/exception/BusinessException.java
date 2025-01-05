package com.example.inventory.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends BaseException {

    public BusinessException(HttpStatus httpStatus, String errorCode, String errorMessage) {
        super(httpStatus, errorCode, errorMessage);
    }

    public BusinessException(String errorMessage) {
        super(HttpStatus.CONFLICT, null, errorMessage);
    }
}
