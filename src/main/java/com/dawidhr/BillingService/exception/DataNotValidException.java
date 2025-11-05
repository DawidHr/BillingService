package com.dawidhr.BillingService.exception;

import org.springframework.stereotype.Component;

@Component
public class DataNotValidException extends RuntimeException {
    public DataNotValidException(String message) {
        super(message);
    }
}
