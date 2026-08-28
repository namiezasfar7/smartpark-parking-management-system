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
public class MySQLParkingSessionRepository
        implements ParkingSessionRepository {

    //DECLARE ATTRIBUTES
    private final VehicleRepository vehicleRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;

    //DATE/TIME FORMATTER
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern(
                    "yyyy-MM-dd HH:mm:ss"
            );

    //DECLARE CONSTRUCTOR
    public MySQLParkingSessionRepository(
            VehicleRepository vehicleRepository,
            ParkingSpaceRepository parkingSpaceRepository
    ) {

        this.vehicleRepository =
                vehicleRepository;

        this.parkingSpaceRepository =
                parkingSpaceRepository;
    }

    //SAVE PARKING SESSION
    @Override
    public void save(ParkingSession parkingSession) {

        if (parkingSession == null) {
            return;
        }

        String sql =
                "INSERT INTO parking_sessions " +
                        "(session_id, registration_number, space_id, zone_id, " +
                        "entry_time, exit_time, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    parkingSession.getSessionId()
            );

            statement.setString(
                    2,
                    parkingSession.getVehicle()
                            .getRegistrationNumber()
            );

            statement.setString(
                    3,
                    parkingSession.getParkingSpace()
                            .getSpaceId()
            );

            statement.setString(
                    4,
                    parkingSession.getParkingSpace()
                            .getZoneId()
            );

            statement.setTimestamp(
                    5,
                    Timestamp.valueOf(
                            parkingSession.getEntryTime()
                    )
            );

            if (parkingSession.getExitTime() != null) {

                statement.setTimestamp(
                        6,
                        Timestamp.valueOf(
                                parkingSession.getExitTime()
                        )
                );

            }
            else {

                statement.setNull(
                        6,
                        java.sql.Types.TIMESTAMP
                );
            }

            statement.setString(
                    7,
                    parkingSession.getStatus().name()
            );

            statement.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to save parking session.",
                    e
            );
        }
    }

    //FIND PARKING SESSION
    @Override
    public ParkingSession findBySessionId(
            String sessionId
    ) {

        if (sessionId == null ||
                sessionId.trim().isEmpty()) {

            return null;
        }

        String sql =
                "SELECT * FROM parking_sessions " +
                        "WHERE session_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(
                    1,
                    sessionId
            );

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                return buildParkingSession(
                        resultSet
                );
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to find parking session.",
                    e
            );
        }

        return null;
    }

    //GET ALL PARKING SESSIONS
    @Override
    public List<ParkingSession> findAll() {

        List<ParkingSession> parkingSessions =
                new ArrayList<>();

        String sql =
                "SELECT * FROM parking_sessions " +
                        "ORDER BY entry_time DESC";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet =
                     statement.executeQuery()) {

            while (resultSet.next()) {

                ParkingSession parkingSession =
                        buildParkingSession(
                                resultSet
                        );

                if (parkingSession != null) {
                    parkingSessions.add(
                            parkingSession
                    );
                }
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to retrieve parking sessions.",
                    e
            );
        }

        return parkingSessions;
    }

    //UPDATE PARKING SESSION
    @Override
    public void update(ParkingSession parkingSession) {

        if (parkingSession == null) {
            return;
        }

        String sql =
                "UPDATE parking_sessions " +
                        "SET exit_time = ?, status = ? " +
                        "WHERE session_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            if (parkingSession.getExitTime() != null) {

                statement.setTimestamp(
                        1,
                        Timestamp.valueOf(
                                parkingSession.getExitTime()
                        )
                );

            }
            else {

                statement.setNull(
                        1,
                        java.sql.Types.TIMESTAMP
                );
            }

            statement.setString(
                    2,
                    parkingSession.getStatus().name()
            );

            statement.setString(
                    3,
                    parkingSession.getSessionId()
            );

            statement.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException(
                    "Failed to update parking session.",
                    e
            );
        }
    }

    //BUILD PARKING SESSION
    private ParkingSession buildParkingSession(
            ResultSet resultSet
    ) throws SQLException {

        String id =
                resultSet.getString("session_id");

        String registrationNumber =
                resultSet.getString(
                        "registration_number"
                );

        String spaceId =
                resultSet.getString("space_id");

        Timestamp entryTimestamp =
                resultSet.getTimestamp("entry_time");

        String entryTime = null;

        if (entryTimestamp != null) {

            entryTime =
                    entryTimestamp
                            .toLocalDateTime()
                            .format(
                                    DATE_TIME_FORMATTER
                            );
        }

        String exitTime = null;

        Timestamp exitTimestamp =
                resultSet.getTimestamp("exit_time");

        if (exitTimestamp != null) {

            exitTime =
                    exitTimestamp
                            .toLocalDateTime()
                            .format(
                                    DATE_TIME_FORMATTER
                            );
        }

        String statusValue =
                resultSet.getString("status");

        ParkingSessionStatus status =
                ParkingSessionStatus.valueOf(
                        statusValue
                );

        Vehicle vehicle =
                vehicleRepository
                        .findByRegistrationNumber(
                                registrationNumber
                        );

        ParkingSpace parkingSpace =
                parkingSpaceRepository
                        .findBySpaceId(spaceId);

        ParkingSession parkingSession =
                new ParkingSession(
                        id,
                        vehicle,
                        parkingSpace,
                        entryTime
                );

        parkingSession.setExitTime(
                exitTime
        );

        parkingSession.setStatus(
                status
        );

        return parkingSession;
    }
}