package com.example.urlshortener.exception;

public class AliasAlreadyExistException extends RuntimeException{
    public AliasAlreadyExistException(String message){
        super(message);
    }
}
