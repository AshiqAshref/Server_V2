package com.MainServer.Server_V2.exception;

public class DuplicateValueException extends RuntimeException {
    public DuplicateValueException(String message){
        super(message);
    }
}
