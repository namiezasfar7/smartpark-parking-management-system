package com.smartpark.repository.memory;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.repository.ParkingSessionRepository;

import java.util.ArrayList;
import java.util.List;

//IN MEMORY PARKING SESSION REPOSITORY
public class InMemoryParkingSessionRepository
        implements ParkingSessionRepository {

    //DECLARE ATTRIBUTES
    private final List<ParkingSession> parkingSessions;

    //DECLARE CONSTRUCTOR
    public InMemoryParkingSessionRepository() {

        parkingSessions =
                new ArrayList<>();
    }

    //SAVE PARKING SESSION
    @Override
    public void save(ParkingSession parkingSession) {

        if (parkingSession == null) {
            return;
        }

        parkingSessions.add(
                parkingSession
        );
    }

    //FIND PARKING SESSION
    @Override
    public ParkingSession findBySessionId(
            String sessionId
    ) {

        if (sessionId == null) {
            return null;
        }

        for (ParkingSession parkingSession :
                parkingSessions) {

            if (sessionId.equals(
                    parkingSession.getSessionId()
            )) {

                return parkingSession;
            }
        }

        return null;
    }

    //GET ALL PARKING SESSIONS
    @Override
    public List<ParkingSession> findAll() {

        return parkingSessions;
    }

    //UPDATE PARKING SESSION
    @Override
    public void update(
            ParkingSession parkingSession
    ) {

        //IN-MEMORY OBJECTS ARE ALREADY UPDATED
        //NOTHING ELSE IS REQUIRED.
    }
}