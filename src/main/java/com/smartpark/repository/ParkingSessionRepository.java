package com.smartpark.repository;

//IMPORTS
import com.smartpark.model.ParkingSession;
import java.util.List;

//PARKING SESSION REPOSITORY INTERFACE
public interface ParkingSessionRepository {

    //DECLARE METHODS
    //SAVE PARKING SESSION
    void save(ParkingSession parkingSession);

    //UPDATE PARKING SPACE
    void update(ParkingSession parkingSession);

    //FIND PARKING SESSION
    ParkingSession findBySessionId(String sessionId);

    //GET ALL PARKING SESSIONS
    List <ParkingSession> findAll();
}