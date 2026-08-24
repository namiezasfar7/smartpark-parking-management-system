package com.smartpark.service;

//IMPORTS
import com.smartpark.exception.ParkingSpaceUnavailableException;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.List;

//PARKING SERVICE CLASS
public class ParkingService {

    //DECLARE ATTRIBUTES
    private final ParkingSpaceRepository parkingSpaceRepository;

    //DECLARE CONSTRUCTOR
    public ParkingService(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    //DECLARE METHODS
    //ADD PARKING SPACE
    public void addParkingSpace(ParkingSpace parkingSpace) {

        //CHECK CONDITION
        if (parkingSpace == null) {
            return;
        }

        parkingSpaceRepository.save(parkingSpace);
    }

    //FIND PARKING SPACE
    public ParkingSpace findParkingSpace(String spaceId) {

        //CHECK CONDITION
        if (spaceId == null) {
            return null;
        }

        return parkingSpaceRepository.findBySpaceId(spaceId);
    }

    //GET ALL PARKING SPACES
    public List<ParkingSpace> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll();
    }

    //UPDATE PARKING SPACE STATUS
    public void updateParkingSpaceStatus(String spaceId, ParkingSpaceStatus status) {

        ParkingSpace parkingSpace = findParkingSpace(spaceId);

        //CHECK CONDITION
        if (parkingSpace == null || status == null) {
            return;
        }

        //CHECK IF SPACE IS ALREADY OCCUPIED
        if (status == ParkingSpaceStatus.OCCUPIED && parkingSpace.getStatus() == ParkingSpaceStatus.OCCUPIED) {
            throw new ParkingSpaceUnavailableException("Parking space is unavailable: " + spaceId);
        }

        parkingSpace.setStatus(status);
    }
}