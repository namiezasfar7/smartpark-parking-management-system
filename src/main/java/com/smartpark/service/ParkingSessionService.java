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
    public ParkingSessionService(
            ParkingSessionRepository parkingSessionRepository,
            ParkingSpaceRepository parkingSpaceRepository
    ) {

        this.parkingSessionRepository =
                parkingSessionRepository;

        this.parkingSpaceRepository =
                parkingSpaceRepository;
    }

    //START SESSION
    public void startSession(ParkingSession session) {

        if (session == null) {

            throw new IllegalArgumentException(
                    "Parking session cannot be null."
            );
        }

        Vehicle vehicle =
                session.getVehicle();

        if (vehicle == null) {

            throw new VehicleNotFoundException(
                    "Vehicle cannot be null."
            );
        }

        //CHECK FOR ACTIVE SESSION
        for (ParkingSession existingSession :
                parkingSessionRepository.findAll()) {

            if (existingSession == null) {
                continue;
            }

            if (existingSession.getVehicle() != null
                    && existingSession.getVehicle()
                    .getRegistrationNumber()
                    .equals(
                            vehicle.getRegistrationNumber()
                    )
                    && existingSession.getStatus()
                    == ParkingSessionStatus.ACTIVE) {

                throw new ActiveSessionException(
                        "Vehicle already has an active " +
                                "parking session: " +
                                vehicle.getRegistrationNumber()
                );
            }
        }

        ParkingSpace parkingSpace =
                session.getParkingSpace();

        if (parkingSpace == null) {

            throw new IllegalArgumentException(
                    "Parking space cannot be null."
            );
        }

        ParkingSpace actualSpace =
                parkingSpaceRepository.findBySpaceId(
                        parkingSpace.getSpaceId()
                );

        if (actualSpace == null) {

            throw new IllegalArgumentException(
                    "Parking space does not exist."
            );
        }

        if (actualSpace.getStatus()
                != ParkingSpaceStatus.AVAILABLE) {

            throw new ParkingSpaceUnavailableException(
                    "Parking space is unavailable: " +
                            actualSpace.getSpaceId()
            );
        }

        //UPDATE DATABASE PARKING SPACE
        parkingSpaceRepository.updateStatus(
                actualSpace.getSpaceId(),
                ParkingSpaceStatus.OCCUPIED
        );

        //SAVE SESSION
        parkingSessionRepository.save(
                session
        );
    }

    //FIND SESSION
    public ParkingSession findSession(
            String sessionId
    ) {

        if (sessionId == null ||
                sessionId.trim().isEmpty()) {

            return null;
        }

        return parkingSessionRepository
                .findBySessionId(sessionId);
    }

    //GET ALL SESSIONS
    public List<ParkingSession> getAllSessions() {

        return parkingSessionRepository.findAll();
    }

    //COMPLETE SESSION
    public void completeSession(
            String sessionId
    ) {

        ParkingSession session =
                parkingSessionRepository
                        .findBySessionId(
                                sessionId
                        );

        if (session == null) {

            throw new IllegalArgumentException(
                    "Parking session not found."
            );
        }

        if (session.getStatus()
                == ParkingSessionStatus.COMPLETED) {

            return;
        }

        //COMPLETE JAVA OBJECT
        session.completeSession();

        //UPDATE SESSION IN DATABASE
        parkingSessionRepository.update(
                session
        );

        //GET PARKING SPACE
        ParkingSpace parkingSpace =
                session.getParkingSpace();

        if (parkingSpace != null) {

            ParkingSpace actualSpace =
                    parkingSpaceRepository.findBySpaceId(
                            parkingSpace.getSpaceId()
                    );

            if (actualSpace != null) {

                //UPDATE DATABASE
                parkingSpaceRepository.updateStatus(
                        actualSpace.getSpaceId(),
                        ParkingSpaceStatus.AVAILABLE
                );
            }
        }
    }
}