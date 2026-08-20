package com.smartpark.repository;

//IMPORTS
import com.smartpark.model.Vehicle;
import java.util.List;

//VEHICLE REPOSITORY INTERFACE
public interface VehicleRepository {

    //DECLARE METHODS
    //SAVE VEHICLE
    void save(Vehicle vehicle);

    //FIND VEHICLE
    Vehicle findByRegistrationNumber(String registrationNumber);

    //GET ALL VEHICLES
    List <Vehicle> findAll();
}