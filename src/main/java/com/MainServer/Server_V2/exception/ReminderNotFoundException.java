package com.MainServer.Server_V2.exception;

public class ReminderNotFoundException extends RuntimeException{
    public ReminderNotFoundException(String message){
        super(message);
    }
}
