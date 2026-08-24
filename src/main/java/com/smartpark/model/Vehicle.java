package com.smartpark.model;

//VEHICLE CLASS
public class Vehicle {

    //DECLARE ATTRIBUTES
    private String registrationNumber;
    private String ownerName;
    private VehicleType vehicleType;

    //DECLARE CONSTRUCTOR
    public Vehicle(String registrationNumber, String ownerName, VehicleType vehicleType) {

        this.registrationNumber = registrationNumber;
        this.ownerName = ownerName;
        this.vehicleType = vehicleType;
    }

    //DECLARE GETTERS
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    //DECLARE SETTERS
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    //DECLARE METHODS
    //TO STRING
    @Override
    public String toString() {
        return "Vehicle{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }
}