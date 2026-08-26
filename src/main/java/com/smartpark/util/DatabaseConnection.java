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
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
