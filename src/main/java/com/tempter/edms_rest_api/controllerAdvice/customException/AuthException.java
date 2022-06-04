package com.tempter.edms_rest_api.controllerAdvice.customException;

public class AuthException extends RuntimeException{
    public AuthException(String message) {
        super(message);
    }
}
