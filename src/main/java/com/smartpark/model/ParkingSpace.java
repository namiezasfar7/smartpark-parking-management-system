package com.smartpark.model;

//PARKING SPACE CLASS
public class ParkingSpace {

    //DECLARE ATTRIBUTES
    private String spaceId;
    private String zoneId;
    private VehicleType vehicleType;
    private ParkingSpaceStatus status;

    //DECLARE CONSTRUCTOR
    public ParkingSpace(String spaceId, String zoneId, VehicleType vehicleType) {
        this.spaceId = spaceId;
        this.zoneId = zoneId;
        this.vehicleType = vehicleType;
        this.status = ParkingSpaceStatus.AVAILABLE;
    }

    //DECLARE GETTERS
    public String getSpaceId() {
        return spaceId;
    }

    public String getZoneId() {
        return zoneId;
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
    //IS AVAILABLE
    public boolean isAvailable() {
        return status == ParkingSpaceStatus.AVAILABLE;
    }

    //TO STRING
    @Override
    public String toString() {
        return spaceId;
    }
}