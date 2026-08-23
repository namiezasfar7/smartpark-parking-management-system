package com.smartpark.repository.memory;

// IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.repository.ParkingSessionRepository;

import java.util.ArrayList;
import java.util.List;

// IN MEMORY PARKING SESSION REPOSITORY
public class InMemoryParkingSessionRepository
        implements ParkingSessionRepository {

    private final List<ParkingSession> parkingSessions;


    // CONSTRUCTOR
    public InMemoryParkingSessionRepository() {

        parkingSessions =
                new ArrayList<>();
    }


    // SAVE SESSION
    @Override
    public void save(
            ParkingSession parkingSession
    ) {

        if (parkingSession == null) {
            return;
        }


        ParkingSession existing =
                findBySessionId(
                        parkingSession.getSessionId()
                );


        if (existing == null) {

            parkingSessions.add(
                    parkingSession
            );
        }
    }


    // FIND SESSION
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


    // GET ALL SESSIONS
    @Override
    public List<ParkingSession> findAll() {

        return parkingSessions;
    }
}