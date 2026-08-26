package com.smartpark.controller;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.model.ParkingZone;
import com.smartpark.repository.ParkingZoneRepository;
import com.smartpark.service.ParkingService;

import java.util.Collections;
import java.util.List;

//PARKING CONTROLLER CLASS
class ParkingController {

    //DECLARE ATTRIBUTES
    private final ParkingService parkingService;
    private final ParkingZoneRepository parkingZoneRepository;

    //DECLARE CONSTRUCTOR
    public ParkingController(ParkingService parkingService, ParkingZoneRepository parkingZoneRepository) {
        this.parkingService = parkingService;
        this.parkingZoneRepository = parkingZoneRepository;
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

    //GET ALL ZONES
    public List <ParkingZone> getAllZones() {

        //CHECK CONDITION
        if (parkingZoneRepository == null) {
            return Collections.emptyList();
        }
        return parkingZoneRepository.findAll();
    }

    //GET PARKING SPACES BY ZONE
    public List <ParkingSpace> getParkingSpacesByZone(String zoneId) {

        //CHECK CONDITION
        if (parkingZoneRepository == null || zoneId == null) {
            return Collections.emptyList();
        }

        ParkingZone zone = parkingZoneRepository.findByZoneId(zoneId);

        //CHECK CONDITION
        if (zone == null) {
            return Collections.emptyList();
        }
        return zone.getSpaces();
    }
}