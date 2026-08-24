package com.smartpark.util;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
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

    //VALIDATE PARKING SPACE
    public static void validateParkingSpace(ParkingSpace parkingSpace) {

        //CHECK CONDITION
        if (parkingSpace == null) {
            throw new IllegalArgumentException("Parking space cannot be null.");
        }

        validateSpaceId(parkingSpace.getSpaceId());

        //CHECK CONDITION
        if (parkingSpace.getVehicleType() == null) {
            throw new IllegalArgumentException("Parking space vehicle type cannot be null.");
        }
    }

    //VALIDATE SPACE ID
    public static void validateSpaceId(String spaceId) {

        //CHECK CONDITION
        if (spaceId == null || spaceId.trim().isEmpty()) {
            throw new IllegalArgumentException("Parking space ID cannot be null or empty.");
        }
    }

    //VALIDATE PARKING SPACE STATUS
    public static void validateParkingSpaceStatus(ParkingSpaceStatus status) {

        //CHECK CONDITION
        if (status == null) {
            throw new IllegalArgumentException("Parking space status cannot be null.");
        }
    }
}