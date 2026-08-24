package com.smartpark.exception;

//ACTIVE SESSION EXCEPTION CLASS
public class ActiveSessionException extends RuntimeException {

    //DECLARE CONSTRUCTOR
    public ActiveSessionException(String message) {
        super(message);
    }
}