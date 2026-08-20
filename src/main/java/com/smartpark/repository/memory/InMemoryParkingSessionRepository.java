package com.smartpark.repository.memory;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.repository.ParkingSessionRepository;

import java.util.ArrayList;
import java.util.List;

//IN MEMORY PARKING SESSION REPOSITORY CLASS
public class InMemoryParkingSessionRepository implements ParkingSessionRepository {

    //DECLARE ATTRIBUTES
    private List <ParkingSession> parkingSessions;

    //DECLARE CONSTRUCTOR
    public InMemoryParkingSessionRepository(){
        this.parkingSessions = new ArrayList<>();
    }

    //DECLARE METHODS
    //SAVE PARKING SESSION
    @Override
    public void save(ParkingSession parkingSession) {
        parkingSessions.add(parkingSession);
    }

    //FIND PARKING SESSION
    @Override
    public ParkingSession findBySessionId(String sessionId){

        //LOOP UNTIL CONDITION IS TRUE
        for(ParkingSession parkingSession : parkingSessions){

            //CHECK CONDITION
            if (parkingSession.getSessionId().equals(sessionId)) {
                return parkingSession;
            }
        }

        return null;
    }

    //GET ALL PARKING SESSIONS
    @Override
    public List <ParkingSession> findAll() {
        return parkingSessions;
    }
}