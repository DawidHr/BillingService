package com.dawidhr.BillingService.service;

import com.dawidhr.BillingService.exception.DataAlreadyExistException;
import com.dawidhr.BillingService.exception.DataNotFoundException;
import com.dawidhr.BillingService.exception.DataNotValidException;
import com.dawidhr.BillingService.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviser {

    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<String> handleAlreadyExists() {
        return ResponseEntity.internalServerError().body("Data already Exists");
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException() {
        return ResponseEntity.internalServerError().body("Data not found");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorizedException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
