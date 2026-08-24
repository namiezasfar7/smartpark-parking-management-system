package com.smartpark.service;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


//ANALYTICS SERVICE CLASS
public class AnalyticsService {

    //DECLARE ATTRIBUTES
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingSessionRepository parkingSessionRepository;

    //DATE/TIME FORMATTER
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //DECLARE CONSTRUCTOR
    public AnalyticsService(ParkingSpaceRepository parkingSpaceRepository, ParkingSessionRepository parkingSessionRepository) {
        this.parkingSpaceRepository = parkingSpaceRepository;
        this.parkingSessionRepository = parkingSessionRepository;
    }

    //DECLARE GETTERS
    //TOTAL SPACES
    public int getTotalSpaces() {
        return parkingSpaceRepository.findAll().size();
    }

    //AVAILABLE SPACES
    public int getAvailableSpaces() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        //LOOP UNTIL CONDITION IS TRUE
        for(ParkingSpace parkingSpace : parkingSpaceRepository.findAll()) {
            //CHECK CONDITION
            if (parkingSpace != null && parkingSpace.getStatus() == ParkingSpaceStatus.AVAILABLE) {
                count++;
            }
        }

        return count;
    }

    //OCCUPIED SPACES
    public int getOccupiedSpaces() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSpace parkingSpace : parkingSpaceRepository.findAll()) {
            //CHECK CONDITION
            if (parkingSpace != null && parkingSpace.getStatus() == ParkingSpaceStatus.OCCUPIED) {
                count++;
            }
        }

        return count;
    }

    //OUT OF SERVICE SPACES
    public int getOutOfServiceSpaces() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSpace parkingSpace : parkingSpaceRepository.findAll()) {
            //CHECK CONDITION
            if (parkingSpace != null && parkingSpace.getStatus() == ParkingSpaceStatus.OUT_OF_SERVICE) {
                count++;
            }
        }

        return count;
    }

    //TOTAL SESSIONS
    public int getTotalSessions() {
        return parkingSessionRepository.findAll().size();
    }

    //ACTIVE SESSIONS
    public int getActiveSessions() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSession session : parkingSessionRepository.findAll()) {
            //CHECK CONDITION
            if (session != null && session.getStatus() == ParkingSessionStatus.ACTIVE) {
                count++;
            }
        }

        return count;
    }

    //COMPLETED SESSIONS
    public int getCompletedSessions() {

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSession session : parkingSessionRepository.findAll()) {
            //CHECK CONDITION
            if (session != null && session.getStatus() == ParkingSessionStatus.COMPLETED) {
                count++;
            }
        }

        return count;
    }

    //OTHER SESSIONS
    public int getOtherSessions() {
        return Math.max(0, getTotalSessions() - getCompletedSessions() - getActiveSessions());
    }

    //AVERAGE DURATION IN MINUTES
    public long getAverageDurationMinutes() {

        //DECLARE VARIABLES
        long totalMinutes = 0;
        int completedCount = 0;

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSession session : parkingSessionRepository.findAll()) {

            //CHECK CONDITION
            if (session == null) {
                continue;
            }

            //CHECK CONDITION
            if (session.getStatus() != ParkingSessionStatus.COMPLETED) {
                continue;
            }

            String entryTime = session.getEntryTime();
            String exitTime = session.getExitTime();

            //CHECK CONDITION
            if (entryTime == null || exitTime == null) {
                continue;
            }

            LocalDateTime entryDateTime = parseDateTime(entryTime);
            LocalDateTime exitDateTime = parseDateTime(exitTime);

            //CHECK CONDITION
            if (entryDateTime == null || exitDateTime == null) {
                continue;
            }

            long minutes = Duration.between(entryDateTime, exitDateTime).toMinutes();

            //CHECK CONDITION
            if (minutes >= 0) {
                totalMinutes += minutes;
                completedCount++;
            }
        }

        //CHECK CONDITION
        if (completedCount == 0) {
            return 0;
        }

        return totalMinutes / completedCount;
    }

    //FORMATTED AVERAGE DURATION
    public String getAverageDurationFormatted() {

        //DECLARE ATTRIBUTES
        long minutes = getAverageDurationMinutes();
        long hours = minutes / 60;
        long remainingMinutes = minutes % 60;

        //CHECK CONDITION
        if (hours > 0) {
            return hours + "h " + remainingMinutes + "m";
        }

        return remainingMinutes + "m";
    }

    //GET ALL SESSIONS
    public List <ParkingSession> getAllSessions() {
        return new ArrayList<>(parkingSessionRepository.findAll());
    }

    //GET SESSIONS FOR A PARTICULAR DATE
    public int getSessionsForDate(LocalDate date) {

        //CHECK CONDITION
        if (date == null) {
            return 0;
        }

        //DECLARE TEMPORARY VARIABLE
        int count = 0;

        //LOOP UNTIL CONDITION IS TRUE
        for (ParkingSession session : parkingSessionRepository.findAll()) {
            //CHECK CONDITION
            if (session == null) {
                continue;
            }

            String entryTime = session.getEntryTime();

            //CHECK CONDITION
            if (entryTime == null) {
                continue;
            }

            LocalDateTime entryDateTime = parseDateTime(entryTime);

            //CHECK CONDITION
            if (entryDateTime == null) {
                continue;
            }

            //CHECK CONDITION
            if (entryDateTime.toLocalDate().equals(date)) {
                count++;
            }
        }

        return count;
    }

    //GET LAST 7 DAYS
    public int[] getLastSevenDaysCounts() {

        //DECLARE ARRAY
        int[] values = new int[7];

        //DECLARE ATTRIBUTE
        LocalDate today = LocalDate.now();

        //LOOP UNTIL CONDITION IS TRUE
        for (int i = 0; i < 7; i++) {

            LocalDate date = today.minusDays(6 - i);

            values[i] = getSessionsForDate(date);
        }

        return values;
    }

    //PARSE DATE/TIME STRING
    private LocalDateTime parseDateTime(String dateTime) {

        //CHECK CONDITION
        if (dateTime == null || dateTime.trim().isEmpty()) {
            return null;
        }

        //CHECK CONDITION
        try {
            return LocalDateTime.parse(dateTime.trim(), DATE_TIME_FORMATTER);

        }
        catch (DateTimeParseException e) {
            return null;
        }
    }
}