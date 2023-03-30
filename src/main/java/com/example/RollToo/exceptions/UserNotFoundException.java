package com.example.RollToo.exceptions;

public class UserNotFoundException extends Throwable{
    public UserNotFoundException(String message){
        super(message);
    }
}
