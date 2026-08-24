package com.smartpark.service;

//IMPORTS
import com.smartpark.model.Vehicle;
import com.smartpark.repository.VehicleRepository;

import java.util.List;

//VEHICLE SERVICE CLASS
public class VehicleService {

    //DECLARE ATTRIBUTES
    private final VehicleRepository vehicleRepository;

    //DECLARE CONSTRUCTOR
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    //DECLARE METHODS
    //REGISTER VEHICLE
    public void registerVehicle(Vehicle vehicle) {

        //CHECK CONDITION
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }

        //CHECK IF VEHICLE ALREADY EXISTS
        Vehicle existingVehicle = vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber());

        if (existingVehicle != null) {
            throw new IllegalArgumentException("Vehicle already registered: " + vehicle.getRegistrationNumber());
        }

        vehicleRepository.save(vehicle);
    }

    //FIND VEHICLE
    public Vehicle findVehicle(String registrationNumber) {

        //CHECK CONDITION
        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            return null;
        }

        return vehicleRepository.findByRegistrationNumber(registrationNumber);
    }

    //GET ALL VEHICLES
    public List <Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}