package com.dawidhr.BillingService.service;

import com.dawidhr.BillingService.exception.DataAlreadyExistException;
import com.dawidhr.BillingService.exception.DataNotFoundException;
import com.dawidhr.BillingService.exception.DataNotValidException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviser {

    @ExceptionHandler(DataNotValidException.class)
    public ResponseEntity<String> handle() {
        return ResponseEntity.internalServerError().body("Could not read file storage");
    }

    @ExceptionHandler(DataAlreadyExistException.class)
    public ResponseEntity<String> handleAlreadyExists() {
        return ResponseEntity.internalServerError().body("Data already Exists");
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException() {
        return ResponseEntity.internalServerError().body("Data not found");
    }
}
