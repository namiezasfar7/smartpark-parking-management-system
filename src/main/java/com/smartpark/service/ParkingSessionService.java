package com.smartpark.service;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.List;

//PARKING SESSION SERVICE CLASS
public class ParkingSessionService {

    //DECLARE ATTRIBUTES
    private final ParkingSessionRepository parkingSessionRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;

    //DECLARE CONSTRUCTOR
    public ParkingSessionService(ParkingSessionRepository parkingSessionRepository, ParkingSpaceRepository parkingSpaceRepository) {
        this.parkingSessionRepository = parkingSessionRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    //DECLARE METHODS
    //START SESSION
    public void startSession(ParkingSession session) {

        //CHECK CONDITION
        if (session == null) {
            throw new IllegalArgumentException("Parking session cannot be null.");
        }

        ParkingSpace parkingSpace = session.getParkingSpace();

        //CHECK CONDITION
        if (parkingSpace == null) {
            throw new IllegalArgumentException("Parking space cannot be null.");
        }

        //GET THE ACTUAL REPOSITORY OBJECT
        ParkingSpace actualSpace = parkingSpaceRepository.findBySpaceId(parkingSpace.getSpaceId());

        //CHECK CONDITION
        if (actualSpace == null) {
            throw new IllegalArgumentException("Parking space does not exist.");
        }

        //CHECK CONDITION
        if (actualSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {
            throw new IllegalStateException("Parking space is not available.");
        }

        actualSpace.setStatus(ParkingSpaceStatus.OCCUPIED);

        parkingSessionRepository.save(session);
    }

    //FIND SESSION
    public ParkingSession findSession(String sessionId) {
        return parkingSessionRepository.findBySessionId(sessionId);
    }

    //GET ALL SESSIONS
    public List <ParkingSession> getAllSessions() {
        return parkingSessionRepository.findAll();
    }

    //COMPLETE SESSION
    public void completeSession(String sessionId) {

        ParkingSession session = parkingSessionRepository.findBySessionId(sessionId);

        //CHECK CONDITION
        if (session == null) {
            throw new IllegalArgumentException("Parking session not found.");
        }

        //CHECK CONDITION
        if (session.getStatus() == ParkingSessionStatus.COMPLETED) {
            return;
        }

        session.completeSession();

        ParkingSpace parkingSpace = session.getParkingSpace();

        //CHECK CONDITION
        if (parkingSpace != null) {

            ParkingSpace actualSpace = parkingSpaceRepository.findBySpaceId(parkingSpace.getSpaceId());

            //CHECK CONDITION
            if (actualSpace != null) {
                actualSpace.setStatus(ParkingSpaceStatus.AVAILABLE);
            }
        }
    }
}