package com.dawidhr.BillingService.exception;

import org.springframework.stereotype.Component;

@Component
public class DataAlreadyExistException extends RuntimeException {
    public DataAlreadyExistException(String message) {
        super(message);
    }
}
