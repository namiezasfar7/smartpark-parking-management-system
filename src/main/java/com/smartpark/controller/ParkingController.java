package com.smartpark.controller;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.service.ParkingService;

import java.util.List;

//PARKING CONTROLLER CLASS
public class ParkingController {

    //DECLARE ATTRIBUTES
    private final ParkingService parkingService;

    //DECLARE CONSTRUCTOR
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    //DECLARE METHODS
    //ADD PARKING SPACE
    public void addParkingSpace(ParkingSpace parkingSpace) {
        parkingService.addParkingSpace(parkingSpace);
    }

    //FIND PARKING SPACE
    public ParkingSpace findParkingSpace(String spaceId) {
        return parkingService.findParkingSpace(spaceId);
    }

    //GET ALL PARKING SPACES
    public List <ParkingSpace> getAllParkingSpaces() {
        return parkingService.getAllParkingSpaces();
    }

    //UPDATE PARKING SPACE STATUS
    public void updateParkingSpaceStatus(String spaceId, ParkingSpaceStatus status) {
        parkingService.updateParkingSpaceStatus(spaceId, status);
    }
}