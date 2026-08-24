package com.smartpark.util;

//IMPORTS
import com.smartpark.model.Vehicle;

//VALIDATION UTILITY CLASS
public final class ValidationUtil {

    //PRIVATE CONSTRUCTOR
    private ValidationUtil() {
    }

    //VALIDATE VEHICLE
    public static void validateVehicle(Vehicle vehicle) {

        //CHECK CONDITION
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }

        validateRegistrationNumber(vehicle.getRegistrationNumber());
        validateOwnerName(vehicle.getOwnerName());

        //CHECK CONDITION
        if (vehicle.getVehicleType() == null) {
            throw new IllegalArgumentException("Vehicle type cannot be null.");
        }
    }

    //VALIDATE REGISTRATION NUMBER
    public static void validateRegistrationNumber(String registrationNumber) {

        //CHECK CONDITION
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number cannot be null or empty.");
        }
    }

    //VALIDATE OWNER NAME
    public static void validateOwnerName(String ownerName) {

        //CHECK CONDITION
        if (ownerName == null || ownerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Owner name cannot be null or empty.");
        }
    }
}