package com.smartpark.controller;

//IMPORTS
import com.smartpark.model.Vehicle;
import com.smartpark.service.VehicleService;

import java.util.List;

//VEHICLE CONTROLLER CLASS
public class VehicleController {

    //DECLARE ATTRIBUTES
    private VehicleService vehicleService;

    //DECLARE CONSTRUCTOR
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    //DECLARE METHODS
    //REGISTER VEHICLE
    public void registerVehicle(Vehicle vehicle) {
        vehicleService.registerVehicle(vehicle);
    }

    //FIND VEHICLE
    public Vehicle findVehicle(String registrationNumber) {
        return vehicleService.findVehicle(registrationNumber);
    }

    //GET ALL VEHICLES
    public List<Vehicle> getAllVehicles() {
       return vehicleService.getAllVehicles();
    }
}