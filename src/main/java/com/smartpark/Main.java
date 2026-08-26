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
            ParkingSessionService parkingSessionService = new ParkingSessionService(parkingSessionRepository,
                    parkingSpaceRepository);
            AnalyticsService analyticsService = new AnalyticsService(parkingSpaceRepository,
                    parkingSessionRepository);

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

        repository.save(new ParkingSpace("P-001", "GF", VehicleType.CAR));
        repository.save(new ParkingSpace("P-002", "GF", VehicleType.CAR));
        repository.save(new ParkingSpace("P-003", "GF", VehicleType.CAR));
        repository.save(new ParkingSpace("P-004", "GF", VehicleType.CAR));
        repository.save(new ParkingSpace("P-005", "GF", VehicleType.CAR));
        repository.save(new ParkingSpace("P-006", "GF", VehicleType.CAR));
        repository.save(new ParkingSpace("P-007", "GF", VehicleType.CAR));
        repository.save(new ParkingSpace("P-008", "GF", VehicleType.CAR));
    }
}