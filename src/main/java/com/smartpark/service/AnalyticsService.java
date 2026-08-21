package com.smartpark.service;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;

import java.util.List;

//ANALYTICS SERVICE CLASS
public class AnalyticsService {

    //DECLARE ATTRIBUTES
    private ParkingSpaceRepository parkingSpaceRepository;
    private ParkingSessionRepository parkingSessionRepository;

    //DECLARE CONSTRUCTOR
    public AnalyticsService(ParkingSpaceRepository parkingSpaceRepository,
                            ParkingSessionRepository parkingSessionRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.parkingSessionRepository = parkingSessionRepository;
    }

    //DECLARE METHODS
    //GET TOTAL SPACES
    public int getTotalSpaces() {
        return parkingSpaceRepository.findAll().size();
    }

    //GET AVAILABLE SPACES
    public int getAvailableSpaces() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        List <ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();

        //LOOP UNTIL CONDITION IS TRUE
        for(ParkingSpace parkingSpace : parkingSpaces) {

            //CHECK CONDITION
            if(parkingSpace.getStatus() == ParkingSpaceStatus.AVAILABLE) {
                count++;
            }
        }

        return count;
    }

    //GET OCCUPIED SPACES
    public int getOccupiedSpaces() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        List <ParkingSpace> parkingSpaces = parkingSpaceRepository.findAll();

        //LOOP UNTIL CONDITION IS TRUE
        for(ParkingSpace parkingSpace : parkingSpaces) {

            //CHECK CONDITION
            if(parkingSpace.getStatus() == ParkingSpaceStatus.OCCUPIED) {
                count++;
            }
        }

        return count;
    }

    //GET ACTIVE SESSIONS
    public int getActiveSessions() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        List <ParkingSession> parkingSessions = parkingSessionRepository.findAll();

        //LOOP UNTIL CONDITION IS TRUE
        for(ParkingSession parkingSession : parkingSessions) {

            //CHECK CONDITION
            if(parkingSession.getStatus() == ParkingSessionStatus.ACTIVE) {
                count++;
            }
        }

        return count;
    }

    //GET COMPLETED SESSIONS
    public int getCompletedSessions() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        List <ParkingSession> parkingSessions = parkingSessionRepository.findAll();

        //LOOP UNTIL CONDITION IS TRUE
        for(ParkingSession parkingSession : parkingSessions) {

            //CHECK CONDITION
            if(parkingSession.getStatus() == ParkingSessionStatus.COMPLETED) {
                count++;
            }
        }

        return count;
    }
}