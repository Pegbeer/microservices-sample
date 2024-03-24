package com.pegbeer.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<Void> handleCustomerAlreadyExistException(CustomerAlreadyExistsException exception, WebRequest webRequest){
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .header("message",exception.getMessage())
                .body(null);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Void> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("message",exception.getMessage())
                .body(null);
    }
}
