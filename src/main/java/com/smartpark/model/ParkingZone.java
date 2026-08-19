package com.smartpark.model;

//IMPORTS
import java.util.ArrayList;
import java.util.List;

//PARKING ZONE CLASS
public class ParkingZone {

    //DECLARE ATTRIBUTES
    private String zoneId;
    private String zoneName;
    private List <ParkingSpace> spaces;

    //DECLARE CONSTRUCTOR
    public ParkingZone(String zoneId, String zoneName) {
        this.zoneId = zoneId;
        this.zoneName = zoneName;
        this.spaces = new ArrayList<>();
    }

    //DECLARE GETTERS
    public String getZoneId() {
        return zoneId;
    }

    public String getZoneName() {
        return zoneName;
    }

    public List <ParkingSpace> getSpaces() {
        return spaces;
    }

    //DECLARE SETTERS
    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    //DECLARE METHODS
    //ADD SPACE
    public void addSpace(ParkingSpace space) {

        //CHECK CONDITION
        if(space != null){
            spaces.add(space);
        }
    }
}