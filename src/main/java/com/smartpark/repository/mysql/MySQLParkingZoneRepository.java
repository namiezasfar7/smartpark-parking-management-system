package com.smartpark.repository.mysql;

//IMPORTS
import com.smartpark.model.ParkingZone;
import com.smartpark.repository.ParkingZoneRepository;
import com.smartpark.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//MYSQL PARKING ZONE REPOSITORY CLASS
public class MySQLParkingZoneRepository implements ParkingZoneRepository {

    //DECLARE METHODS
    //SAVE PARKING ZONE
    @Override
    public void save(ParkingZone parkingZone) {

        String sql = "INSERT INTO parking_zones (zone_id, zone_name) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, parkingZone.getZoneId());
            statement.setString(2, parkingZone.getZoneName());

            statement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //FIND PARKING ZONE
    @Override
    public ParkingZone findByZoneId(String zoneId) {

        String sql = "SELECT * FROM parking_zones WHERE zone_id = ?";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, zoneId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String id = resultSet.getString("zone_id");
                String name = resultSet.getString("zone_name");

                return new ParkingZone(id, name);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    //GET ALL PARKING ZONES
    @Override
    public List <ParkingZone> findAll() {

        List <ParkingZone> parkingZones = new ArrayList<>();

        String sql = "SELECT * FROM parking_zones";

        try (Connection connection = DatabaseConnection.getConnection()) {

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            //LOOP THROUGH RESULTS
            while (resultSet.next()) {

                String id = resultSet.getString("zone_id");
                String name = resultSet.getString("zone_name");

                ParkingZone parkingZone = new ParkingZone(id, name);

                parkingZones.add(parkingZone);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return parkingZones;
    }
}