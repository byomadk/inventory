package com.example.inventory.exception;

import com.example.inventory.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> renderDefaultResponse(Exception ex) {
        System.out.println("Exception occurred: "+ ex);
        ex.printStackTrace();
        return throwError(Constants.DEFAULT_ERROR_CODE, "Exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> renderBusinessErrorResponse(BusinessException ex) {
        System.out.println("Business Exception occurred: "+ ex);
        ex.printStackTrace();
        return throwError(ex.getErrorCode(), ex.getErrorMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ErrorResponse> renderTechnicalErrorResponse(TechnicalException ex) {
        System.out.println("Technical Exception occurred: "+ ex);
        ex.printStackTrace();
        return throwError(ex.getErrorCode(), "Technical Exception occurred", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> throwError(String errCode, String errMessage, HttpStatus httpStatus){
        return new ResponseEntity<>(new ErrorResponse(errCode, errMessage), httpStatus);
    }
}
