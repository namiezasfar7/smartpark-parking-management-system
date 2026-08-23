package com.smartpark.repository.memory;

// IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.ArrayList;
import java.util.List;

// IN MEMORY PARKING SPACE REPOSITORY
public class InMemoryParkingSpaceRepository
        implements ParkingSpaceRepository {

    private final List<ParkingSpace> parkingSpaces;


    // CONSTRUCTOR
    public InMemoryParkingSpaceRepository() {

        parkingSpaces =
                new ArrayList<>();
    }


    // SAVE PARKING SPACE
    @Override
    public void save(
            ParkingSpace parkingSpace
    ) {

        if (parkingSpace == null) {
            return;
        }


        // PREVENT DUPLICATE SPACE IDs
        ParkingSpace existing =
                findBySpaceId(
                        parkingSpace.getSpaceId()
                );


        if (existing == null) {

            parkingSpaces.add(
                    parkingSpace
            );

        } else {

            existing.setStatus(
                    parkingSpace.getStatus()
            );
        }
    }


    // FIND PARKING SPACE
    @Override
    public ParkingSpace findBySpaceId(
            String spaceId
    ) {

        if (spaceId == null) {
            return null;
        }


        for (ParkingSpace parkingSpace :
                parkingSpaces) {

            if (spaceId.equals(
                    parkingSpace.getSpaceId()
            )) {

                return parkingSpace;
            }
        }


        return null;
    }


    // GET ALL PARKING SPACES
    @Override
    public List<ParkingSpace> findAll() {

        return parkingSpaces;
    }
}