package com.smartpark.model;

//PARKING SPACE CLASS
public class ParkingSpace {

    //DECLARE ATTRIBUTES
    private String spaceId;
    private VehicleType vehicleType;
    private ParkingSpaceStatus status;

    //DECLARE CONSTRUCTOR
    public ParkingSpace(String spaceId, VehicleType vehicleType) {
        this.spaceId = spaceId;
        this.vehicleType = vehicleType;
        this.status = ParkingSpaceStatus.AVAILABLE;
    }

    //DECLARE GETTERS
    public String getSpaceId() {
        return spaceId;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public ParkingSpaceStatus getStatus() {
        return status;
    }

    //DECLARE SETTERS
    public void setStatus(ParkingSpaceStatus status) {
        this.status = status;
    }

    //DECLARE METHODS
    //TO STRING
    @Override
    public String toString() {
        return "ParkingSpace{" +
                "spaceId = '" + spaceId + '\'' +
                ", vehicleType = " + vehicleType +
                ", status = " + status +
                '}';
    }

    //IS AVAILABLE
    public boolean isAvailable() {
        return status == ParkingSpaceStatus.AVAILABLE;
    }
}