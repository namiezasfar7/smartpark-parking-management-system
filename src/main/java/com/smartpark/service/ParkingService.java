package com.smartpark.service;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSpaceRepository;
import com.smartpark.util.ValidationUtil;

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

        //VALIDATE PARKING SPACE
        ValidationUtil.validateParkingSpace(parkingSpace);

        //CHECK IF PARKING SPACE ALREADY EXISTS
        ParkingSpace existingParkingSpace = parkingSpaceRepository.findBySpaceId(parkingSpace.getSpaceId());

        if (existingParkingSpace != null) {
            throw new IllegalArgumentException("Parking space already exists: " + parkingSpace.getSpaceId());
        }

        parkingSpaceRepository.save(parkingSpace);
    }

    //FIND PARKING SPACE
    public ParkingSpace findParkingSpace(String spaceId) {

        //VALIDATE SPACE ID
        ValidationUtil.validateSpaceId(spaceId);

        return parkingSpaceRepository.findBySpaceId(spaceId);
    }

    //GET ALL PARKING SPACES
    public List <ParkingSpace> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll();
    }

    //UPDATE PARKING SPACE STATUS
    public void updateParkingSpaceStatus(String spaceId, ParkingSpaceStatus status) {

        //VALIDATE INPUT
        ValidationUtil.validateSpaceId(spaceId);
        ValidationUtil.validateParkingSpaceStatus(status);

        ParkingSpace parkingSpace = findParkingSpace(spaceId);

        //CHECK IF PARKING SPACE EXISTS
        if (parkingSpace == null) {
            throw new IllegalArgumentException("Parking space not found: " + spaceId);
        }

        //UPDATE STATUS
        parkingSpace.setStatus(status);
    }
}