package com.smartpark.service;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.List;

//PARKING SERVICE CLASS
public class ParkingService {

    //DECLARE ATTRIBUTES
    private ParkingSpaceRepository parkingSpaceRepository;

    //DECLARE CONSTRUCTOR
    public ParkingService(ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    //DECLARE METHODS
    //ADD PARKING SPACE
    public void addParkingSpace(ParkingSpace parkingSpace) {
        parkingSpaceRepository.save(parkingSpace);
    }

    //FIND PARKING SPACE
    public ParkingSpace findParkingSpace(String spaceId) {
        return parkingSpaceRepository.findBySpaceId(spaceId);
    }

    //GET ALL PARKING SPACES
    public List <ParkingSpace> getAllParkingSpaces() {
        return parkingSpaceRepository.findAll();
    }

    //UPDATE PARKING SPACE STATUS
    public void updateParkingSpaceStatus(String spaceId, ParkingSpaceStatus status) {
        ParkingSpace parkingSpace = parkingSpaceRepository.findBySpaceId(spaceId);

        parkingSpace.setStatus(status);
    }
}