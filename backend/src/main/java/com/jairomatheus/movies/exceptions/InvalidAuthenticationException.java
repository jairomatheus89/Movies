package com.jairomatheus.movies.exceptions;

public class InvalidAuthenticationException extends RuntimeException{

    public InvalidAuthenticationException(String message){
        super(message);
    }
}
