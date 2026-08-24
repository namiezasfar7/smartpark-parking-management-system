package com.smartpark.controller;

//IMPORTS
import com.smartpark.service.AnalyticsService;

//DASHBOARD CONTROLLER CLASS
public class DashboardController {

    //DECLARE ATTRIBUTES
    private final AnalyticsService analyticsService;

    //DECLARE CONSTRUCTOR
    public DashboardController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    //DECLARE METHODS
    //GET TOTAL SPACES
    public int getTotalSpaces() {
        return analyticsService.getTotalSpaces();
    }

    //GET AVAILABLE SPACES
    public int getAvailableSpaces() {
        return analyticsService.getAvailableSpaces();
    }

    //GET OCCUPIED SPACES
    public int getOccupiedSpaces() {
        return analyticsService.getOccupiedSpaces();
    }

    //GET ACTIVE SESSIONS
    public int getActiveSessions() {
        return analyticsService.getActiveSessions();
    }

    //GET COMPLETED SESSIONS
    public int getCompletedSessions() {
        return analyticsService.getCompletedSessions();
    }
}