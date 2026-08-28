package com.smartpark.repository.memory;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.ArrayList;
import java.util.List;

//IN MEMORY PARKING SPACE REPOSITORY
public class InMemoryParkingSpaceRepository implements ParkingSpaceRepository {

    //DECLARE ATTRIBUTES
    private final List<ParkingSpace> parkingSpaces;

    //DECLARE CONSTRUCTOR
    public InMemoryParkingSpaceRepository() {
        parkingSpaces = new ArrayList<>();
    }

    //SAVE PARKING SPACE
    @Override
    public void save(ParkingSpace parkingSpace) {

        if (parkingSpace == null) {
            return;
        }

        ParkingSpace existing =
                findBySpaceId(parkingSpace.getSpaceId());

        if (existing == null) {

            parkingSpaces.add(parkingSpace);

        }
        else {

            existing.setStatus(
                    parkingSpace.getStatus()
            );
        }
    }

    //FIND PARKING SPACE
    @Override
    public ParkingSpace findBySpaceId(String spaceId) {

        if (spaceId == null) {
            return null;
        }

        for (ParkingSpace parkingSpace : parkingSpaces) {

            if (spaceId.equals(
                    parkingSpace.getSpaceId())) {

                return parkingSpace;
            }
        }

        return null;
    }

    //GET ALL PARKING SPACES
    @Override
    public List<ParkingSpace> findAll() {
        return parkingSpaces;
    }

    //UPDATE PARKING SPACE STATUS
    @Override
    public void updateStatus(
            String spaceId,
            ParkingSpaceStatus status
    ) {

        ParkingSpace parkingSpace =
                findBySpaceId(spaceId);

        if (parkingSpace != null && status != null) {

            parkingSpace.setStatus(status);
        }
    }
}