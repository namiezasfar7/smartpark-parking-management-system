package com.smartpark;

//IMPORTS
import com.smartpark.config.Application;
import com.smartpark.ui.MainFrame;
import com.smartpark.util.DatabaseConnection;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

//MAIN CLASS
public class Main {

    public static void main(String[] args) {

        //TEST DATABASE CONNECTION BEFORE STARTING APPLICATION
        if (!DatabaseConnection.testConnection()) {

            SwingUtilities.invokeLater(() -> {

                JOptionPane.showMessageDialog(
                        null,
                        "SmartPark could not connect to the database.\n\n" +
                                "Please check:\n" +
                                "- MySQL is running\n" +
                                "- The 'smartpark' database exists\n" +
                                "- The SMARTPARK_DB_PASSWORD environment variable is set\n" +
                                "- The database username and password are correct",
                        "Database Connection Error",
                        JOptionPane.ERROR_MESSAGE
                );
            });

            return;
        }

        //START APPLICATION
        SwingUtilities.invokeLater(() -> {

            //CREATE APPLICATION
            Application application =
                    new Application();

            //CREATE MAIN FRAME
            MainFrame mainFrame =
                    new MainFrame(
                            application.getVehicleController(),
                            application.getParkingController(),
                            application.getParkingSessionController(),
                            application.getDashboardController(),
                            application.getAnalyticsService()
                    );

            //SHOW APPLICATION
            mainFrame.setVisible(true);
        });
    }
}