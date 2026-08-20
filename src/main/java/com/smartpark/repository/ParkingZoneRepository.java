package com.smartpark.repository;

//IMPORTS
import com.smartpark.model.ParkingZone;
import java.util.List;

//PARKING ZONE REPOSITORY INTERFACE
public interface ParkingZoneRepository {

    //DECLARE METHODS
    //SAVE PARKING ZONE
    void save(ParkingZone parkingZone);

    //FIND PARKING ZONE
    ParkingZone findByZoneId(String zoneId);

    //GET ALL PARKING ZONE
    List <ParkingZone> findAll();
}