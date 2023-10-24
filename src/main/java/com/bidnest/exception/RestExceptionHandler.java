package com.bidnest.exception;

import com.bidnest.dto.ExceptionDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = { RestException.class })
    public ResponseEntity<ExceptionDTO> handleException(RestException restException) {
        return ResponseEntity
                .status(restException.getResponseStatus())
                .body(new ExceptionDTO(restException.getMessage()));
    }
}