package com.pegbeer.accounts.exception;

import lombok.Getter;

public class InvalidPathVariableException extends RuntimeException{

    @Getter
    private String fieldName;

    @Getter
    private String message;

    public InvalidPathVariableException(String fieldName, String message){
        super();
        this.fieldName = fieldName;
        this.message = message;
    }
}
