package com.smartpark.repository.memory;

//IMPORTS
import com.smartpark.model.Vehicle;
import com.smartpark.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

//IN MEMORY VEHICLE REPOSITORY CLASS
public class InMemoryVehicleRepository implements VehicleRepository {

    //DECLARE ATTRIBUTES
    private List <Vehicle> vehicles;

    //DECLARE CONSTRUCTOR
    public InMemoryVehicleRepository(){
        this.vehicles = new ArrayList<>();
    }

    //DECLARE METHODS
    //SAVE VEHICLE
    @Override
    public void save(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    //FIND VEHICLE
    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber){

        //LOOP UNTIL CONDITION IS TRUE
        for(Vehicle vehicle : vehicles){

            //CHECK CONDITION
            if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                return vehicle;
            }
        }

        return null;
    }

    //GET ALL VEHICLES
    @Override
    public List <Vehicle> findAll() {
        return vehicles;
    }
}
