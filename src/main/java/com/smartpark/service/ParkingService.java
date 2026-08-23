package com.smartpark.service;

// IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.List;

// PARKING SERVICE CLASS
public class ParkingService {

    private final ParkingSpaceRepository parkingSpaceRepository;


    // CONSTRUCTOR
    public ParkingService(
            ParkingSpaceRepository parkingSpaceRepository
    ) {

        this.parkingSpaceRepository =
                parkingSpaceRepository;
    }


    // ADD PARKING SPACE
    public void addParkingSpace(
            ParkingSpace parkingSpace
    ) {

        if (parkingSpace == null) {
            return;
        }

        parkingSpaceRepository.save(
                parkingSpace
        );
    }


    // FIND PARKING SPACE
    public ParkingSpace findParkingSpace(
            String spaceId
    ) {

        if (spaceId == null) {
            return null;
        }

        return parkingSpaceRepository.findBySpaceId(
                spaceId
        );
    }


    // GET ALL PARKING SPACES
    public List<ParkingSpace> getAllParkingSpaces() {

        return parkingSpaceRepository.findAll();
    }


    // UPDATE PARKING SPACE STATUS
    public void updateParkingSpaceStatus(
            String spaceId,
            ParkingSpaceStatus status
    ) {

        ParkingSpace parkingSpace =
                findParkingSpace(spaceId);


        if (parkingSpace == null ||
                status == null) {

            return;
        }


        parkingSpace.setStatus(status);
    }
}