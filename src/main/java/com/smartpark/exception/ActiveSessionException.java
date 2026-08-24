package com.smartpark.exception;

//ACTIVE SESSION EXCEPTION
public class ActiveSessionException extends RuntimeException {

    //DECLARE CONSTRUCTOR
    public ActiveSessionException(String message) {
        super(message);
    }
}