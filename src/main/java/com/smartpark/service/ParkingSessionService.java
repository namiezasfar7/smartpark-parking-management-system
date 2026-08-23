package com.smartpark.service;

// IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.List;

// PARKING SESSION SERVICE
public class ParkingSessionService {

    private final ParkingSessionRepository parkingSessionRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;


    // CONSTRUCTOR
    public ParkingSessionService(
            ParkingSessionRepository parkingSessionRepository,
            ParkingSpaceRepository parkingSpaceRepository
    ) {

        this.parkingSessionRepository =
                parkingSessionRepository;

        this.parkingSpaceRepository =
                parkingSpaceRepository;
    }


    //=========================================================
    // START SESSION
    //=========================================================

    public void startSession(
            ParkingSession session
    ) {

        if (session == null) {
            throw new IllegalArgumentException(
                    "Parking session cannot be null."
            );
        }


        ParkingSpace parkingSpace =
                session.getParkingSpace();


        if (parkingSpace == null) {
            throw new IllegalArgumentException(
                    "Parking space cannot be null."
            );
        }


        // GET THE ACTUAL REPOSITORY OBJECT
        ParkingSpace actualSpace =
                parkingSpaceRepository.findBySpaceId(
                        parkingSpace.getSpaceId()
                );


        if (actualSpace == null) {
            throw new IllegalArgumentException(
                    "Parking space does not exist."
            );
        }


        if (actualSpace.getStatus() !=
                ParkingSpaceStatus.AVAILABLE) {

            throw new IllegalStateException(
                    "Parking space is not available."
            );
        }


        actualSpace.setStatus(
                ParkingSpaceStatus.OCCUPIED
        );


        parkingSessionRepository.save(
                session
        );
    }


    //=========================================================
    // FIND SESSION
    //=========================================================

    public ParkingSession findSession(
            String sessionId
    ) {

        return parkingSessionRepository.findBySessionId(
                sessionId
        );
    }


    //=========================================================
    // GET ALL SESSIONS
    //=========================================================

    public List<ParkingSession> getAllSessions() {

        return parkingSessionRepository.findAll();
    }


    //=========================================================
    // COMPLETE SESSION
    //=========================================================

    public void completeSession(
            String sessionId
    ) {

        ParkingSession session =
                parkingSessionRepository.findBySessionId(
                        sessionId
                );


        if (session == null) {

            throw new IllegalArgumentException(
                    "Parking session not found."
            );
        }


        if (session.getStatus() ==
                ParkingSessionStatus.COMPLETED) {

            return;
        }


        session.completeSession();


        ParkingSpace parkingSpace =
                session.getParkingSpace();


        if (parkingSpace != null) {

            ParkingSpace actualSpace =
                    parkingSpaceRepository.findBySpaceId(
                            parkingSpace.getSpaceId()
                    );


            if (actualSpace != null) {

                actualSpace.setStatus(
                        ParkingSpaceStatus.AVAILABLE
                );
            }
        }
    }
}