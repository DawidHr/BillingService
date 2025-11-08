package com.dawidhr.BillingService.exception;

import org.springframework.stereotype.Component;

@Component
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
