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
    public ParkingService(
            ParkingSpaceRepository parkingSpaceRepository
    ) {

        this.parkingSpaceRepository =
                parkingSpaceRepository;
    }

    //ADD PARKING SPACE
    public void addParkingSpace(ParkingSpace parkingSpace) {

        ValidationUtil.validateParkingSpace(
                parkingSpace
        );

        ParkingSpace existingParkingSpace =
                parkingSpaceRepository.findBySpaceId(
                        parkingSpace.getSpaceId()
                );

        if (existingParkingSpace != null) {

            throw new IllegalArgumentException(
                    "Parking space already exists: " +
                            parkingSpace.getSpaceId()
            );
        }

        parkingSpaceRepository.save(
                parkingSpace
        );
    }

    //FIND PARKING SPACE
    public ParkingSpace findParkingSpace(
            String spaceId
    ) {

        ValidationUtil.validateSpaceId(
                spaceId
        );

        return parkingSpaceRepository.findBySpaceId(
                spaceId
        );
    }

    //GET ALL PARKING SPACES
    public List<ParkingSpace> getAllParkingSpaces() {

        return parkingSpaceRepository.findAll();
    }

    //UPDATE PARKING SPACE STATUS
    public void updateParkingSpaceStatus(
            String spaceId,
            ParkingSpaceStatus status
    ) {

        ValidationUtil.validateSpaceId(
                spaceId
        );

        ValidationUtil.validateParkingSpaceStatus(
                status
        );

        ParkingSpace parkingSpace =
                findParkingSpace(spaceId);

        if (parkingSpace == null) {

            throw new IllegalArgumentException(
                    "Parking space not found: " +
                            spaceId
            );
        }

        parkingSpaceRepository.updateStatus(
                spaceId,
                status
        );
    }
}