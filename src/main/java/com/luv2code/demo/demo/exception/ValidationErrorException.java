package com.luv2code.demo.demo.exception;

public class ValidationErrorException extends RuntimeException{
    public ValidationErrorException(String message) {
        super(message);
    }
}
