package com.tempter.edms_rest_api.controllerAdvice.customException;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }
}
