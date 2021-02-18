package com.luv2code.demo.demo.controllerAdvices;

import com.luv2code.demo.demo.exceptions.ValidationErrorException;
import com.luv2code.demo.demo.modals.responses.AuthMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({ValidationErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<AuthMessageResponse> handleException(Exception exception){
        AuthMessageResponse authMessageResponse = new AuthMessageResponse();
        authMessageResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        authMessageResponse.setMessage(exception.getMessage());
        authMessageResponse.setTimeStamp(System.currentTimeMillis());
        authMessageResponse.setError(true);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.APPLICATION_JSON).body(authMessageResponse);
    }

}
