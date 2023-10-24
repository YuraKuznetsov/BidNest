package com.bidnest.exception;

import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends RestException {

    public ItemNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
