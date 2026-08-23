package com.smartpark.config;

//IMPORTS
import com.smartpark.controller.DashboardController;
import com.smartpark.controller.ParkingController;
import com.smartpark.controller.ParkingSessionController;
import com.smartpark.controller.VehicleController;

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


//APPLICATION CLASS
public class Application {

    //DECLARE ATTRIBUTES
    //DECLARE REPOSITORIES
    private final VehicleRepository vehicleRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingZoneRepository parkingZoneRepository;
    private final ParkingSessionRepository parkingSessionRepository;

    //DECLARE SERVICES
    private final VehicleService vehicleService;
    private final ParkingService parkingService;
    private final ParkingSessionService parkingSessionService;
    private final AnalyticsService analyticsService;

    //DECLARE CONTROLLERS
    private final VehicleController vehicleController;
    private final ParkingController parkingController;
    private final ParkingSessionController parkingSessionController;
    private final DashboardController dashboardController;

    //DECLARE CONSTRUCTOR
    public Application() {

        //CREATE REPOSITORIES
        vehicleRepository =
                new InMemoryVehicleRepository();

        parkingSpaceRepository =
                new InMemoryParkingSpaceRepository();

        parkingZoneRepository =
                new InMemoryParkingZoneRepository();

        parkingSessionRepository =
                new InMemoryParkingSessionRepository();

        //CREATE SERVICES
        vehicleService =
                new VehicleService(
                        vehicleRepository
                );

        parkingService =
                new ParkingService(
                        parkingSpaceRepository
                );

        parkingSessionService =
                new ParkingSessionService(
                        parkingSessionRepository,
                        parkingSpaceRepository
                );

        analyticsService =
                new AnalyticsService(
                        parkingSpaceRepository,
                        parkingSessionRepository
                );

        //CREATE CONTROLLERS
        vehicleController =
                new VehicleController(
                        vehicleService
                );

        parkingController =
                new ParkingController(
                        parkingService
                );

        parkingSessionController =
                new ParkingSessionController(
                        parkingSessionService
                );

        dashboardController =
                new DashboardController(
                        analyticsService
                );
    }

    //GETTERS - CONTROLLERS
    public VehicleController getVehicleController() {
        return vehicleController;
    }

    public ParkingController getParkingController() {
        return parkingController;
    }

    public ParkingSessionController getParkingSessionController() {
        return parkingSessionController;
    }

    public DashboardController getDashboardController() {
        return dashboardController;
    }
}