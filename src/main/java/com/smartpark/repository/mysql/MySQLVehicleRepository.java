package com.smartpark.repository.mysql;

//IMPORTS
import com.smartpark.model.Vehicle;
import com.smartpark.model.VehicleType;
import com.smartpark.repository.VehicleRepository;
import com.smartpark.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//MYSQL VEHICLE REPOSITORY CLASS
public class MySQLVehicleRepository implements VehicleRepository {

    //DECLARE METHODS
    //SAVE VEHICLE
    @Override
    public void save(Vehicle vehicle) {

        if (vehicle == null) {
            return;
        }

        String sql =
                "INSERT INTO vehicles " +
                        "(registration_number, owner_name, vehicle_type) " +
                        "VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vehicle.getRegistrationNumber());
            statement.setString(2, vehicle.getOwnerName());
            statement.setString(3, vehicle.getVehicleType().name());

            statement.executeUpdate();

        }
        catch (SQLException e) {
            throw new RuntimeException("Failed to save vehicle.", e);
        }
    }

    //FIND VEHICLE
    @Override
    public Vehicle findByRegistrationNumber(String registrationNumber) {

        if (registrationNumber == null || registrationNumber.trim().isEmpty()) {
            return null;
        }

        String sql =
                "SELECT * FROM vehicles " +
                        "WHERE registration_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, registrationNumber);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String registration =
                        resultSet.getString("registration_number");

                String ownerName =
                        resultSet.getString("owner_name");

                VehicleType vehicleType =
                        VehicleType.valueOf(
                                resultSet.getString("vehicle_type")
                        );

                return new Vehicle(
                        registration,
                        ownerName,
                        vehicleType
                );
            }

        }
        catch (SQLException e) {
            throw new RuntimeException("Failed to find vehicle.", e);
        }

        return null;
    }

    //GET ALL VEHICLES
    @Override
    public List<Vehicle> findAll() {

        List<Vehicle> vehicles = new ArrayList<>();

        String sql = "SELECT * FROM vehicles";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {

                String registration =
                        resultSet.getString("registration_number");

                String ownerName =
                        resultSet.getString("owner_name");

                VehicleType vehicleType =
                        VehicleType.valueOf(
                                resultSet.getString("vehicle_type")
                        );

                Vehicle vehicle =
                        new Vehicle(
                                registration,
                                ownerName,
                                vehicleType
                        );

                vehicles.add(vehicle);
            }

        }
        catch (SQLException e) {
            throw new RuntimeException("Failed to retrieve vehicles.", e);
        }

        return vehicles;
    }
}