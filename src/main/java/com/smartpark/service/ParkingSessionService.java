package com.smartpark.service;

//IMPORTS
import com.smartpark.exception.ActiveSessionException;
import com.smartpark.exception.ParkingSpaceUnavailableException;
import com.smartpark.exception.VehicleNotFoundException;

import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.model.Vehicle;

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

        //GET VEHICLE
        Vehicle vehicle = session.getVehicle();

        //CHECK VEHICLE
        if (vehicle == null) {
            throw new VehicleNotFoundException("Vehicle cannot be null.");
        }

        //CHECK IF VEHICLE ALREADY HAS AN ACTIVE SESSION
        for (ParkingSession existingSession : parkingSessionRepository.findAll()) {

            if (existingSession.getVehicle() != null
                    && existingSession.getVehicle().getRegistrationNumber().equals(vehicle.getRegistrationNumber())
                    && existingSession.getStatus() == ParkingSessionStatus.ACTIVE) {

                throw new ActiveSessionException("Vehicle already has an active parking session: " + vehicle.getRegistrationNumber());
            }
        }

        //GET PARKING SPACE
        ParkingSpace parkingSpace = session.getParkingSpace();

        //CHECK PARKING SPACE
        if (parkingSpace == null) {
            throw new IllegalArgumentException("Parking space cannot be null.");
        }

        //GET ACTUAL REPOSITORY OBJECT
        ParkingSpace actualSpace = parkingSpaceRepository.findBySpaceId(parkingSpace.getSpaceId());

        //CHECK IF SPACE EXISTS
        if (actualSpace == null) {
            throw new IllegalArgumentException("Parking space does not exist.");
        }

        //CHECK PARKING SPACE AVAILABILITY
        if (actualSpace.getStatus() != ParkingSpaceStatus.AVAILABLE) {
            throw new ParkingSpaceUnavailableException("Parking space is unavailable: " + actualSpace.getSpaceId());
        }

        //OCCUPY PARKING SPACE
        actualSpace.setStatus(ParkingSpaceStatus.OCCUPIED);

        //SAVE SESSION
        parkingSessionRepository.save(session);
    }

    //FIND SESSION
    public ParkingSession findSession(String sessionId) {

        if (sessionId == null || sessionId.trim().isEmpty()) {
            return null;
        }

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

        //COMPLETE SESSION
        session.completeSession();

        //GET PARKING SPACE
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