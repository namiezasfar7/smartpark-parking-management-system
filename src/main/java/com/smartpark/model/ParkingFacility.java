package com.smartpark.model;

//IMPORTS
import java.util.ArrayList;
import java.util.List;

//PARKING FACILITY CLASS
public class ParkingFacility {

    //DECLARE ATTRIBUTES
    private String facilityId;
    private String facilityName;
    private List <ParkingZone> zones;

    //DECLARE CONSTRUCTOR
    public ParkingFacility(String facilityId, String facilityName) {
        this.facilityId = facilityId;
        this.facilityName = facilityName;
        this.zones = new ArrayList<>();
    }

    //DECLARE GETTERS
    public String getFacilityId() {
        return facilityId;
    }

    public String getFacilityName() {
        return facilityName;
    }

    public List <ParkingZone> getZones() {
        return zones;
    }

    //DECLARE SETTERS
    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    //DECLARE METHODS
    //ADD ZONE
    public void addZone(ParkingZone zone) {

        //CHECK CONDITION
        if(zone != null){
            zones.add(zone);
        }
    }
}