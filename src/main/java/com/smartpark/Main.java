package com.smartpark;

//IMPORTS
import com.smartpark.controller.DashboardController;
import com.smartpark.controller.ParkingController;
import com.smartpark.controller.ParkingSessionController;
import com.smartpark.controller.VehicleController;

import com.smartpark.model.ParkingSpace;
import com.smartpark.model.VehicleType;

import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;
import com.smartpark.repository.VehicleRepository;

import com.smartpark.repository.memory.InMemoryParkingSessionRepository;
import com.smartpark.repository.memory.InMemoryParkingSpaceRepository;
import com.smartpark.repository.memory.InMemoryVehicleRepository;

import com.smartpark.service.AnalyticsService;
import com.smartpark.service.ParkingService;
import com.smartpark.service.ParkingSessionService;
import com.smartpark.service.VehicleService;

import com.smartpark.ui.MainFrame;

import javax.swing.SwingUtilities;

//MAIN CLASS
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            //CREATE REPOSITORIES
            VehicleRepository vehicleRepository = new InMemoryVehicleRepository();
            ParkingSpaceRepository parkingSpaceRepository = new InMemoryParkingSpaceRepository();
            ParkingSessionRepository parkingSessionRepository = new InMemoryParkingSessionRepository();

            //CREATE INITIAL PARKING SPACES
            createInitialParkingSpaces(parkingSpaceRepository);

            //CREATE SERVICES
            VehicleService vehicleService = new VehicleService(vehicleRepository);
            ParkingService parkingService = new ParkingService(parkingSpaceRepository);
            ParkingSessionService parkingSessionService = new ParkingSessionService(parkingSessionRepository, parkingSpaceRepository);
            AnalyticsService analyticsService = new AnalyticsService(parkingSpaceRepository, parkingSessionRepository);

            //CREATE CONTROLLERS
            VehicleController vehicleController = new VehicleController(vehicleService);
            ParkingController parkingController = new ParkingController(parkingService);
            ParkingSessionController parkingSessionController = new ParkingSessionController(parkingSessionService);
            DashboardController dashboardController = new DashboardController(analyticsService);

            //CREATE MAIN FRAME
            MainFrame mainFrame = new MainFrame(
                    vehicleController,
                    parkingController,
                    parkingSessionController,
                    dashboardController,
                    analyticsService
            );

            //SHOW APPLICATION
            mainFrame.setVisible(true);
        });
    }

    //CREATE INITIAL PARKING SPACES
    private static void createInitialParkingSpaces(ParkingSpaceRepository repository) {

        //GROUND FLOOR - 8 SPACES
        for (int i = 1; i <= 8; i++) {
            repository.save(new ParkingSpace(String.format("P-%03d", i), "GF", VehicleType.CAR));
        }

        //LEVEL 1 - 8 SPACES
        for (int i = 9; i <= 16; i++) {
            repository.save(new ParkingSpace(String.format("P-%03d", i), "L1", VehicleType.CAR));
        }

        //LEVEL 2 - 8 SPACES
        for (int i = 17; i <= 24; i++) {
            repository.save(new ParkingSpace(String.format("P-%03d", i), "L2", VehicleType.CAR));
        }

        //LEVEL 3 - 8 SPACES
        for (int i = 25; i <= 32; i++) {
            repository.save(new ParkingSpace(String.format("P-%03d", i), "L3", VehicleType.CAR));
        }
    }
}