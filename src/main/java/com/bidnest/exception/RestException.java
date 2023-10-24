package com.bidnest.exception;

import org.springframework.http.HttpStatus;

public class RestException extends RuntimeException {

    private final HttpStatus responseStatus;

    public RestException(HttpStatus responseStatus, String responseMessage) {
        super(responseMessage);
        this.responseStatus = responseStatus;
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }
}
