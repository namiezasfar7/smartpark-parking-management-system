package com.smartpark.repository.memory;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.repository.ParkingSessionRepository;

import java.util.ArrayList;
import java.util.List;

//IN MEMORY PARKING SESSION REPOSITORY
public class InMemoryParkingSessionRepository implements ParkingSessionRepository {

    //DECLARE ATTRIBUTES
    private final List <ParkingSession> parkingSessions;

    //DECLARE CONSTRUCTOR
    public InMemoryParkingSessionRepository() {
        parkingSessions = new ArrayList<>();
    }

    //DECLARE METHODS
    //SAVE SESSION
    @Override
    public void save(ParkingSession parkingSession) {

        //CHECK CONDITION
        if (parkingSession == null) {
            return;
        }

        ParkingSession existing = findBySessionId(parkingSession.getSessionId());

        //CHECK CONDITION
        if (existing == null) {
            parkingSessions.add(parkingSession);
        }
    }

    //FIND SESSION
    @Override
    public ParkingSession findBySessionId(String sessionId) {

        //CHECK CONDITION
        if (sessionId == null) {
            return null;
        }

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSession parkingSession : parkingSessions) {
            //CHECK CONDITION
            if (sessionId.equals(parkingSession.getSessionId())) {
                return parkingSession;
            }
        }

        return null;
    }

    //GET ALL SESSIONS
    @Override
    public List <ParkingSession> findAll() {
        return parkingSessions;
    }
}