package com.luv2code.demo.demo.exceptions;

public class ValidationErrorException extends RuntimeException{
    public ValidationErrorException(String message) {
        super(message);
    }
}
