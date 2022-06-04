package com.tempter.edms_rest_api.controllerAdvice;

import com.tempter.edms_rest_api.controllerAdvice.customException.AuthException;
import com.tempter.edms_rest_api.controllerAdvice.customException.EntityNotFoundException;
import com.tempter.edms_rest_api.controllerAdvice.customException.SignatureException;
import com.tempter.edms_rest_api.controllerAdvice.customException.UnsupportedMediaException;
import com.tempter.edms_rest_api.dto.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler({EntityNotFoundException.class,UnsupportedMediaException.class,SignatureException.class,
            AuthException.class})
    public ResponseEntity<MessageResponse> exceptionHandler(RuntimeException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new MessageResponse(true, e.getMessage()));
    }

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<MessageResponse> maxUploadSizeExceededException(RuntimeException e) {
        log.error(e.getMessage());
        return ResponseEntity.badRequest().body(new MessageResponse(true,"Maximum upload size exceeded. Max size: "+ maxFileSize));
    }


}
