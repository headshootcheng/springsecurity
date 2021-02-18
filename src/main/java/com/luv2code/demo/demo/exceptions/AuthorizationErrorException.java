package com.luv2code.demo.demo.exceptions;

public class AuthorizationErrorException extends RuntimeException{
    public AuthorizationErrorException(String message) {
        super(message);
    }
}
