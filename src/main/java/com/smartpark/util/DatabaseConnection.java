package com.smartpark.util;

//IMPORTS
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DATABASE CONNECTION CLASS
public class DatabaseConnection {

    //DECLARE ATTRIBUTES
    private static final String URL = "jdbc:mysql://localhost:3306/smartpark";
    private static final String USERNAME = "root";
    private static final String PASSWORD = System.getenv("SMARTPARK_DB_PASSWORD");

    //DECLARE METHODS
    //GET DATABASE CONNECTION
    public static Connection getConnection() throws SQLException {

        //CHECK DATABASE PASSWORD
        if (PASSWORD == null || PASSWORD.trim().isEmpty()) {
            throw new SQLException(
                    "Database password not found. " +
                            "Please set the SMARTPARK_DB_PASSWORD environment variable."
            );
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    //TEST DATABASE CONNECTION
    public static boolean testConnection() {

        try (Connection connection = getConnection()) {

            return connection != null && !connection.isClosed();

        }
        catch (SQLException e) {

            System.err.println("Database connection failed.");
            System.err.println(e.getMessage());

            return false;
        }
    }
}