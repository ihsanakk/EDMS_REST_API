package com.tempter.edms_rest_api.controllerAdvice.customException;

public class SignatureException extends RuntimeException{

    public SignatureException(String message) {
        super(message);
    }
}
