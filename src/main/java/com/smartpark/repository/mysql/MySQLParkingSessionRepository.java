package com.smartpark.repository.mysql;

//IMPORTS
import com.smartpark.model.ParkingSession;
import com.smartpark.model.ParkingSessionStatus;
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.Vehicle;
import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;
import com.smartpark.repository.VehicleRepository;
import com.smartpark.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

//MYSQL PARKING SESSION REPOSITORY CLASS
public class MySQLParkingSessionRepository implements ParkingSessionRepository {

    //DECLARE ATTRIBUTES
    private VehicleRepository vehicleRepository;
    private ParkingSpaceRepository parkingSpaceRepository;

    //DATE/TIME FORMATTER
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //DECLARE CONSTRUCTOR
    public MySQLParkingSessionRepository(VehicleRepository vehicleRepository, ParkingSpaceRepository parkingSpaceRepository) {
        this.vehicleRepository = vehicleRepository;
        this.parkingSpaceRepository = parkingSpaceRepository;
    }

    //SAVE PARKING SESSION
    @Override
    public void save(ParkingSession parkingSession) {

        String sql = "INSERT INTO parking_sessions " +
                "(session_id, registration_number, space_id, zone_id, entry_time, exit_time, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, parkingSession.getSessionId());
            statement.setString(2, parkingSession.getVehicle().getRegistrationNumber());
            statement.setString(3, parkingSession.getParkingSpace().getSpaceId());
            statement.setString(4, parkingSession.getParkingSpace().getZoneId());

            statement.setTimestamp(5, Timestamp.valueOf(parkingSession.getEntryTime()));

            if (parkingSession.getExitTime() != null) {
                statement.setTimestamp(6, Timestamp.valueOf(parkingSession.getExitTime()));

            }
            else {
                statement.setNull(6, java.sql.Types.TIMESTAMP);
            }

            statement.setString(7, parkingSession.getStatus().name());

            statement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //FIND PARKING SESSION
    @Override
    public ParkingSession findBySessionId(String sessionId) {

        String sql = "SELECT * FROM parking_sessions WHERE session_id = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, sessionId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String id = resultSet.getString("session_id");
                String registrationNumber = resultSet.getString("registration_number");
                String spaceId = resultSet.getString("space_id");
                String entryTime = resultSet.getTimestamp("entry_time").toLocalDateTime().format(DATE_TIME_FORMATTER);

                String exitTime = null;

                Timestamp exitTimestamp = resultSet.getTimestamp("exit_time");

                if (exitTimestamp != null) {
                    exitTime = exitTimestamp.toLocalDateTime().format(DATE_TIME_FORMATTER);
                }

                ParkingSessionStatus status = ParkingSessionStatus.valueOf(resultSet.getString("status"));
                Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
                ParkingSpace parkingSpace = parkingSpaceRepository.findBySpaceId(spaceId);
                ParkingSession parkingSession = new ParkingSession(id, vehicle, parkingSpace, entryTime);

                parkingSession.setExitTime(exitTime);
                parkingSession.setStatus(status);

                return parkingSession;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //GET ALL PARKING SESSIONS
    @Override
    public List <ParkingSession> findAll() {

        List <ParkingSession> parkingSessions =
                new ArrayList<>();

        String sql = "SELECT * FROM parking_sessions";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            //LOOP THROUGH RESULTS
            while (resultSet.next()) {

                String id = resultSet.getString("session_id");
                String registrationNumber = resultSet.getString("registration_number");
                String spaceId = resultSet.getString("space_id");
                String entryTime = resultSet.getTimestamp("entry_time").toLocalDateTime().format(DATE_TIME_FORMATTER);

                String exitTime = null;

                Timestamp exitTimestamp = resultSet.getTimestamp("exit_time");

                if (exitTimestamp != null) {
                    exitTime = exitTimestamp.toLocalDateTime().format(DATE_TIME_FORMATTER);
                }

                ParkingSessionStatus status = ParkingSessionStatus.valueOf(resultSet.getString("status"));
                Vehicle vehicle = vehicleRepository.findByRegistrationNumber(registrationNumber);
                ParkingSpace parkingSpace = parkingSpaceRepository.findBySpaceId(spaceId);
                ParkingSession parkingSession = new ParkingSession(id, vehicle, parkingSpace, entryTime);

                parkingSession.setExitTime(exitTime);
                parkingSession.setStatus(status);

                parkingSessions.add(parkingSession);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return parkingSessions;
    }
}