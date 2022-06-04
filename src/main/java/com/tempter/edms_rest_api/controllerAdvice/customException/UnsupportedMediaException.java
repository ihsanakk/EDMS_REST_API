package com.tempter.edms_rest_api.controllerAdvice.customException;

public class UnsupportedMediaException extends RuntimeException{

    public UnsupportedMediaException(String message) {
        super(message);
    }
}
