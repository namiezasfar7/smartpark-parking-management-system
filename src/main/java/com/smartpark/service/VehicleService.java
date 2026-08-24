package com.smartpark.service;

//IMPORTS
import com.smartpark.model.Vehicle;
import com.smartpark.repository.VehicleRepository;
import com.smartpark.util.ValidationUtil;

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

        //VALIDATE VEHICLE
        ValidationUtil.validateVehicle(vehicle);

        //CHECK IF VEHICLE ALREADY EXISTS
        Vehicle existingVehicle = vehicleRepository.findByRegistrationNumber(vehicle.getRegistrationNumber());

        //CHECK CONDITION
        if (existingVehicle != null) {
            throw new IllegalArgumentException("Vehicle already registered: " + vehicle.getRegistrationNumber());
        }

        vehicleRepository.save(vehicle);
    }

    //FIND VEHICLE
    public Vehicle findVehicle(String registrationNumber) {

        //VALIDATE REGISTRATION NUMBER
        ValidationUtil.validateRegistrationNumber(registrationNumber);

        return vehicleRepository.findByRegistrationNumber(registrationNumber);
    }

    //GET ALL VEHICLES
    public List <Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}