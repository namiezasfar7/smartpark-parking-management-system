package com.smartpark.exception;

//VEHICLE NOT FOUND EXCEPTION CLASS
public class VehicleNotFoundException extends RuntimeException {

    //DECLARE CONSTRUCTOR
    public VehicleNotFoundException(String message) {
        super(message);
    }
}