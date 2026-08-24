package com.smartpark.model;

//IMPORTS
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//PARKING SESSION CLASS
public class ParkingSession {

    //DECLARE ATTRIBUTES
    private String sessionId;
    private Vehicle vehicle;
    private ParkingSpace parkingSpace;
    private String entryTime;
    private String exitTime;
    private ParkingSessionStatus status;

    //DATE/TIME FORMATTER
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //DECLARE CONSTRUCTOR
    public ParkingSession(
            String sessionId,
            Vehicle vehicle,
            ParkingSpace parkingSpace,
            String entryTime
    ) {
        this.sessionId = sessionId;
        this.vehicle = vehicle;
        this.parkingSpace = parkingSpace;
        this.entryTime = entryTime;
        this.status = ParkingSessionStatus.ACTIVE;
    }

    //DECLARE GETTERS
    public String getSessionId() {
        return sessionId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public String getExitTime() {
        return exitTime;
    }

    public ParkingSessionStatus getStatus() {
        return status;
    }

    //DECLARE SETTERS
    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public void setStatus(ParkingSessionStatus status) {
        this.status = status;
    }

    //DECLARE METHODS
    //TO STRING
    @Override
    public String toString() {
        return "ParkingSession{" +
                "sessionId = '" + sessionId + '\'' +
                ", vehicle = " + vehicle +
                ", parking space = " + parkingSpace +
                ", entry time = " + entryTime +
                ", exit time = " + exitTime +
                ", status = " + status +
                '}';
    }

    //COMPLETE SESSION
    public void completeSession() {
        this.exitTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        this.status = ParkingSessionStatus.COMPLETED;
    }
}