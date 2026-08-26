package com.smartpark;

//IMPORTS
import com.smartpark.controller.DashboardController;
import com.smartpark.controller.ParkingController;
import com.smartpark.controller.ParkingSessionController;
import com.smartpark.controller.VehicleController;

import com.smartpark.model.ParkingSpace;
import com.smartpark.model.ParkingZone;
import com.smartpark.model.VehicleType;

import com.smartpark.repository.ParkingSessionRepository;
import com.smartpark.repository.ParkingSpaceRepository;
import com.smartpark.repository.ParkingZoneRepository;
import com.smartpark.repository.VehicleRepository;

import com.smartpark.repository.memory.InMemoryParkingSessionRepository;
import com.smartpark.repository.memory.InMemoryParkingSpaceRepository;
import com.smartpark.repository.memory.InMemoryParkingZoneRepository;
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
            ParkingZoneRepository parkingZoneRepository = new InMemoryParkingZoneRepository();
            ParkingSessionRepository parkingSessionRepository = new InMemoryParkingSessionRepository();

            //CREATE INITIAL PARKING SPACES AND ZONES
            createInitialParkingSpaces(parkingSpaceRepository, parkingZoneRepository);

            //CREATE SERVICES
            VehicleService vehicleService = new VehicleService(vehicleRepository);
            ParkingService parkingService = new ParkingService(parkingSpaceRepository);
            ParkingSessionService parkingSessionService = new ParkingSessionService(parkingSessionRepository,
                    parkingSpaceRepository);
            AnalyticsService analyticsService = new AnalyticsService(parkingSpaceRepository,
                    parkingSessionRepository);

            //CREATE CONTROLLERS
            VehicleController vehicleController = new VehicleController(vehicleService);
            ParkingController parkingController = new ParkingController(parkingService, parkingZoneRepository);
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

    //CREATE INITIAL PARKING SPACES AND ZONES
    private static void createInitialParkingSpaces(ParkingSpaceRepository spaceRepository,
                                                   ParkingZoneRepository zoneRepository) {

        //CREATE ZONES
        ParkingZone groundFloor = new ParkingZone("GROUND", "Ground Floor");
        ParkingZone level1 = new ParkingZone("LEVEL1", "Level 1");
        ParkingZone level2 = new ParkingZone("LEVEL2", "Level 2");
        ParkingZone level3 = new ParkingZone("LEVEL3", "Level 3");

        //FILL EACH ZONE WITH 8 SPACES, NUMBERED SEQUENTIALLY ACROSS ALL ZONES
        int nextSpaceNumber = 1;
        nextSpaceNumber = addSpacesToZone(groundFloor, nextSpaceNumber, 8, spaceRepository);
        nextSpaceNumber = addSpacesToZone(level1, nextSpaceNumber, 8, spaceRepository);
        nextSpaceNumber = addSpacesToZone(level2, nextSpaceNumber, 8, spaceRepository);
        addSpacesToZone(level3, nextSpaceNumber, 8, spaceRepository);

        //SAVE ZONES
        zoneRepository.save(groundFloor);
        zoneRepository.save(level1);
        zoneRepository.save(level2);
        zoneRepository.save(level3);
    }

    //ADD SPACES TO ZONE
    private static int addSpacesToZone(ParkingZone zone, int startNumber, int count,
                                       ParkingSpaceRepository spaceRepository) {

        //LOOP UNTIL CONDITION IS TRUE
        for (int i = 0; i < count; i++) {
            ParkingSpace space = new ParkingSpace(
                    String.format("P-%03d", startNumber + i),
                    zone.getZoneId(),
                    VehicleType.CAR
            );

            zone.addSpace(space);
            spaceRepository.save(space);
        }

        return startNumber + count;
    }
}