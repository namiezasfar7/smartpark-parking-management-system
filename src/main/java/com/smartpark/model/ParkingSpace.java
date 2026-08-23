package com.smartpark.model;

// PARKING SPACE CLASS
public class ParkingSpace {

    private String spaceId;
    private VehicleType vehicleType;
    private ParkingSpaceStatus status;


    // CONSTRUCTOR
    public ParkingSpace(
            String spaceId,
            VehicleType vehicleType
    ) {

        this.spaceId =
                spaceId;

        this.vehicleType =
                vehicleType;

        this.status =
                ParkingSpaceStatus.AVAILABLE;
    }


    // GET SPACE ID
    public String getSpaceId() {

        return spaceId;
    }


    // GET VEHICLE TYPE
    public VehicleType getVehicleType() {

        return vehicleType;
    }


    // GET STATUS
    public ParkingSpaceStatus getStatus() {

        return status;
    }


    // SET STATUS
    public void setStatus(
            ParkingSpaceStatus status
    ) {

        this.status = status;
    }


    // IS AVAILABLE
    public boolean isAvailable() {

        return status ==
                ParkingSpaceStatus.AVAILABLE;
    }


    // TO STRING
    @Override
    public String toString() {

        return spaceId;
    }
}