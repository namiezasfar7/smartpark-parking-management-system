package com.smartpark.service;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.List;

//PARKING SERVICE CLASS
public class ParkingSessionService {

    //DECLARE ATTRIBUTES
    private ParkingSessionRepository parkingSessionRepository;
    private ParkingSpaceRepository parkingSpaceRepository;

    //DECLARE CONSTRUCTOR
    public ParkingSessionService( ParkingSessionRepository parkingSessionRepository,
                                  ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    //DECLARE METHODS
    //START PARKING SESSION
    public void startSession(ParkingSession session) {
        parkingSessionRepository.save(session);
    }

    //FIND PARKING SESSION
    public ParkingSession findSession(String sessionId) {
        return parkingSessionRepository.findBySessionId(sessionId);
    }

    //GET ALL PARKING SESSIONS
    public List <ParkingSession> getAllSessions() {
        return parkingSessionRepository.findAll();
    }

    //COMPLETE SESSION
    public void completeSession(String sessionId) {
        ParkingSession session = parkingSessionRepository.findBySessionId(sessionId);

        session.completeSession();

        ParkingSpace parkingSpace = session.getParkingSpace();
        parkingSpace.setStatus(ParkingSpaceStatus.AVAILABLE);
    }
}