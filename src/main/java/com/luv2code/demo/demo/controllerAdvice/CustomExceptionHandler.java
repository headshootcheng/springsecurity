package com.luv2code.demo.demo.controllerAdvice;

import com.luv2code.demo.demo.exception.ValidationErrorException;
import com.luv2code.demo.demo.modal.response.AuthMessageResponse;
import com.luv2code.demo.demo.modal.response.AuthMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ValidationErrorException.class})
    public ResponseEntity<AuthMessageResponse> handleException(Exception exception){
        AuthMessageResponse authMessageResponse = new AuthMessageResponse();
        authMessageResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        authMessageResponse.setMessage(exception.getMessage());
        authMessageResponse.setTimeStamp(System.currentTimeMillis());
        authMessageResponse.setError(true);
        return new ResponseEntity<>(authMessageResponse, HttpStatus.BAD_REQUEST);
    }

}
