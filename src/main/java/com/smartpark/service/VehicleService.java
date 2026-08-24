package com.smartpark.service;

//IMPORTS
import com.smartpark.exception.VehicleNotFoundException;
import com.smartpark.model.Vehicle;
import com.smartpark.repository.VehicleRepository;

import java.util.List;

//VEHICLE SERVICE CLASS
public class VehicleService {

    //DECLARE ATTRIBUTES
    private VehicleRepository vehicleRepository;

    //DECLARE CONSTRUCTOR
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    //DECLARE METHODS
    //REGISTER VEHICLE
    public void registerVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    //FIND VEHICLE
    public Vehicle findVehicle(String registrationNumber) {

        Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);

        //CHECK CONDITION
        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle not found: " + registrationNumber);
        }

        return vehicle;
    }

    //GET ALL VEHICLES
    public List <Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}