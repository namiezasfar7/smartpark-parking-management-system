package com.smartpark.exception;

//PARKING SPACE UNAVAILABLE EXCEPTION
public class ParkingSpaceUnavailableException extends RuntimeException {

    //DECLARE CONSTRUCTOR
    public ParkingSpaceUnavailableException(String message) {
        super(message);
    }
}