package com.smartpark.repository;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import java.util.List;

//PARKING SPACE REPOSITORY INTERFACE
public interface ParkingSpaceRepository {

    //DECLARE METHODS
    //SAVE PARKING SPACE
    void save(ParkingSpace parkingSpace);

    //FIND PARKING SPACE
    ParkingSpace findBySpaceId(String spaceId);

    //GET ALL PARKING SPACES
    List <ParkingSpace> findAll();
}