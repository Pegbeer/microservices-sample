package com.pegbeer.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerAlreadyExistsException extends RuntimeException{
    public CustomerAlreadyExistsException(){
        super(message);
    }

    public static final String message = "A conflict occurred processing the request";
}
