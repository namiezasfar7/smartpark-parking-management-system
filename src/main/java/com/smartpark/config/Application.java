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

import com.smartpark.repository.mysql.MySQLParkingSessionRepository;
import com.smartpark.repository.mysql.MySQLParkingSpaceRepository;
import com.smartpark.repository.mysql.MySQLParkingZoneRepository;
import com.smartpark.repository.mysql.MySQLVehicleRepository;

import com.smartpark.service.AnalyticsService;
import com.smartpark.service.ParkingService;
import com.smartpark.service.ParkingSessionService;
import com.smartpark.service.VehicleService;

//APPLICATION CLASS
public class Application {

    //DECLARE ATTRIBUTES
    //REPOSITORIES
    private final VehicleRepository vehicleRepository;
    private final ParkingSpaceRepository parkingSpaceRepository;
    private final ParkingZoneRepository parkingZoneRepository;
    private final ParkingSessionRepository parkingSessionRepository;

    //SERVICES
    private final VehicleService vehicleService;
    private final ParkingService parkingService;
    private final ParkingSessionService parkingSessionService;
    private final AnalyticsService analyticsService;

    //CONTROLLERS
    private final VehicleController vehicleController;
    private final ParkingController parkingController;
    private final ParkingSessionController parkingSessionController;
    private final DashboardController dashboardController;

    //DECLARE CONSTRUCTOR
    public Application() {

        //CREATE MYSQL REPOSITORIES
        vehicleRepository =
                new MySQLVehicleRepository();

        parkingSpaceRepository =
                new MySQLParkingSpaceRepository();

        parkingZoneRepository =
                new MySQLParkingZoneRepository();

        parkingSessionRepository =
                new MySQLParkingSessionRepository(
                        vehicleRepository,
                        parkingSpaceRepository
                );

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

    //GET VEHICLE CONTROLLER
    public VehicleController getVehicleController() {
        return vehicleController;
    }

    //GET PARKING CONTROLLER
    public ParkingController getParkingController() {
        return parkingController;
    }

    //GET PARKING SESSION CONTROLLER
    public ParkingSessionController getParkingSessionController() {
        return parkingSessionController;
    }

    //GET DASHBOARD CONTROLLER
    public DashboardController getDashboardController() {
        return dashboardController;
    }

    //GET ANALYTICS SERVICE
    public AnalyticsService getAnalyticsService() {
        return analyticsService;
    }
}