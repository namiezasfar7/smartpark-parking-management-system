package com.smartpark.repository;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;

import java.util.List;

//PARKING SPACE REPOSITORY INTERFACE
public interface ParkingSpaceRepository {

    //SAVE PARKING SPACE
    void save(ParkingSpace parkingSpace);

    //FIND PARKING SPACE
    ParkingSpace findBySpaceId(String spaceId);

    //GET ALL PARKING SPACES
    List<ParkingSpace> findAll();

    //UPDATE PARKING SPACE STATUS
    void updateStatus(String spaceId, ParkingSpaceStatus status);
}