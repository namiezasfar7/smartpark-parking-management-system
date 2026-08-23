package com.smartpark.repository.memory;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.ArrayList;
import java.util.List;

//IN MEMORY PARKING SPACE REPOSITORY
public class InMemoryParkingSpaceRepository implements ParkingSpaceRepository {

    //DECLARE ATTRIBUTES
    private final List <ParkingSpace> parkingSpaces;

    //DECLARE CONSTRUCTOR
    public InMemoryParkingSpaceRepository() {
        parkingSpaces = new ArrayList<>();
    }

    //DECLARE METHODS
    //SAVE PARKING SPACE
    @Override
    public void save(ParkingSpace parkingSpace) {

        //CHECK CONDITION
        if (parkingSpace == null) {
            return;
        }

        // PREVENT DUPLICATE SPACE IDs
        ParkingSpace existing = findBySpaceId(parkingSpace.getSpaceId());

        //CHECK CONDITION
        if (existing == null) {
            parkingSpaces.add(parkingSpace);
        }
        else {
            existing.setStatus(parkingSpace.getStatus());
        }
    }

    //FIND PARKING SPACE
    @Override
    public ParkingSpace findBySpaceId(String spaceId) {

        //CHECK CONDITION
        if (spaceId == null) {
            return null;
        }

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSpace parkingSpace : parkingSpaces) {
            //CHECK CONDITION
            if (spaceId.equals(parkingSpace.getSpaceId())) {
                return parkingSpace;
            }
        }

        return null;
    }

    //GET ALL PARKING SPACES
    @Override
    public List <ParkingSpace> findAll() {
        return parkingSpaces;
    }
}