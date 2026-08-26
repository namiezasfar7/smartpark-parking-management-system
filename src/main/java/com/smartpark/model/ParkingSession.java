package com.smartpark.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParkingSession {

    private String sessionId;
    private Vehicle vehicle;
    private ParkingSpace parkingSpace;
    private ParkingZone parkingZone;
    private String entryTime;
    private String exitTime;
    private ParkingSessionStatus status;

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ParkingSession(
            String sessionId,
            Vehicle vehicle,
            ParkingSpace parkingSpace,
            ParkingZone parkingZone,
            String entryTime
    ) {
        this.sessionId = sessionId;
        this.vehicle = vehicle;
        this.parkingSpace = parkingSpace;
        this.parkingZone = parkingZone;
        this.entryTime = entryTime;
        this.status = ParkingSessionStatus.ACTIVE;
    }

    // Keeps the existing MySQL repository compatible while it loads older sessions.
    public ParkingSession(
            String sessionId,
            Vehicle vehicle,
            ParkingSpace parkingSpace,
            String entryTime
    ) {
        this(
                sessionId,
                vehicle,
                parkingSpace,
                parkingSpace == null || parkingSpace.getZoneId() == null
                        ? null
                        : new ParkingZone(parkingSpace.getZoneId(), parkingSpace.getZoneId()),
                entryTime
        );
    }

    public String getSessionId() {
        return sessionId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpace getParkingSpace() {
        return parkingSpace;
    }

    public ParkingZone getParkingZone() {
        return parkingZone;
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

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public void setExitTime(String exitTime) {
        this.exitTime = exitTime;
    }

    public void setStatus(ParkingSessionStatus status) {
        this.status = status;
    }

    public void completeSession() {
        this.exitTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        this.status = ParkingSessionStatus.COMPLETED;
    }

    @Override
    public String toString() {
        return "ParkingSession{" +
                "sessionId='" + sessionId + '\'' +
                ", vehicle=" + vehicle +
                ", parkingSpace=" + parkingSpace +
                ", parkingZone=" + parkingZone +
                ", entryTime='" + entryTime + '\'' +
                ", exitTime='" + exitTime + '\'' +
                ", status=" + status +
                '}';
    }
}
