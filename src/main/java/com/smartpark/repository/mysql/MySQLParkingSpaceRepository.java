package com.smartpark.repository.mysql;

//IMPORTS
import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingSpaceStatus;
import com.smartpark.model.VehicleType;
import com.smartpark.repository.ParkingSpaceRepository;
import com.smartpark.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//MYSQL PARKING SPACE REPOSITORY CLASS
public class MySQLParkingSpaceRepository implements ParkingSpaceRepository {

    //DECLARE METHODS
    //SAVE PARKING SPACE
    @Override
    public void save(ParkingSpace parkingSpace) {

        String sql = "INSERT INTO parking_spaces (space_id, zone_id, vehicle_type, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, parkingSpace.getSpaceId());
            statement.setString(2, parkingSpace.getZoneId());
            statement.setString(3, parkingSpace.getVehicleType().name());
            statement.setString(4, parkingSpace.getStatus().name());

            statement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //FIND PARKING SPACE
    @Override
    public ParkingSpace findBySpaceId(String spaceId) {

        String sql = "SELECT * FROM parking_spaces WHERE space_id = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, spaceId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String id = resultSet.getString("space_id");
                String zoneId = resultSet.getString("zone_id");

                VehicleType vehicleType = VehicleType.valueOf(resultSet.getString("vehicle_type"));
                ParkingSpaceStatus status = ParkingSpaceStatus.valueOf(resultSet.getString("status"));
                ParkingSpace parkingSpace = new ParkingSpace(id, zoneId, vehicleType);

                parkingSpace.setStatus(status);

                return parkingSpace;
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //GET ALL PARKING SPACES
    @Override
    public List <ParkingSpace> findAll() {

        List <ParkingSpace> parkingSpaces = new ArrayList<>();

        String sql = "SELECT * FROM parking_spaces";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            //LOOP THROUGH RESULTS
            while (resultSet.next()) {

                String id = resultSet.getString("space_id");
                String zoneId = resultSet.getString("zone_id");

                VehicleType vehicleType = VehicleType.valueOf(resultSet.getString("vehicle_type"));
                ParkingSpaceStatus status = ParkingSpaceStatus.valueOf(resultSet.getString("status"));
                ParkingSpace parkingSpace = new ParkingSpace(id, zoneId, vehicleType);

                parkingSpace.setStatus(status);

                parkingSpaces.add(parkingSpace);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return parkingSpaces;
    }
}