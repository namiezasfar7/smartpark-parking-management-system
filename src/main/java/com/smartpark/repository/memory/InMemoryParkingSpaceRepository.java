package com.smartpark.repository.memory;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.ArrayList;
import java.util.List;

//IN MEMORY PARKING SPACE REPOSITORY CLASS
public class InMemoryParkingSpaceRepository implements ParkingSpaceRepository {

    //DECLARE ATTRIBUTES
    private List <ParkingSpace> parkingSpaces;

    //DECLARE CONSTRUCTOR
    public InMemoryParkingSpaceRepository(){
        this.parkingSpaces = new ArrayList<>();
    }

    //DECLARE METHODS
    //SAVE PARKING SPACE
    @Override
    public void save(ParkingSpace parkingSpace) {
        parkingSpaces.add(parkingSpace);
    }

    //FIND PARKING SPACE
    @Override
    public ParkingSpace findBySpaceId(String spaceId){

        //LOOP UNTIL CONDITION IS TRUE
        for(ParkingSpace parkingSpace : parkingSpaces){

            //CHECK CONDITION
            if (parkingSpace.getSpaceId().equals(spaceId)) {
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