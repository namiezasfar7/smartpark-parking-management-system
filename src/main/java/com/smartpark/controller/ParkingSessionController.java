package com.smartpark.controller;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.service.ParkingSessionService;

import java.util.List;

//PARKING SESSION CONTROLLER CLASS
public class ParkingSessionController {

    //DECLARE ATTRIBUTES
    private final ParkingSessionService parkingSessionService;

    //DECLARE CONSTRUCTOR
    public ParkingSessionController(ParkingSessionService parkingSessionService) {
        this.parkingSessionService = parkingSessionService;
    }

    //DECLARE METHODS
    //START PARKING SESSION
    public void startSession(ParkingSession session) {
        parkingSessionService.startSession(session);
    }

    //FIND PARKING SESSION
    public ParkingSession findSession(String sessionId) {
        return parkingSessionService.findSession(sessionId);
    }

    //GET ALL PARKING SESSIONS
    public List <ParkingSession> getAllSessions() {
        return parkingSessionService.getAllSessions();
    }

    //COMPLETE SESSION
    public void completeSession(String sessionId) {
        parkingSessionService.completeSession(sessionId);
    }
}