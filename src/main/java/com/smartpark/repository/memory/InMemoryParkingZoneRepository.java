package com.smartpark.repository.memory;

//IMPORTS
import com.smartpark.model.ParkingZone;
import com.smartpark.repository.ParkingZoneRepository;

import java.util.ArrayList;
import java.util.List;

//IN MEMORY PARKING ZONE REPOSITORY CLASS
public class InMemoryParkingZoneRepository implements ParkingZoneRepository {

    //DECLARE ATTRIBUTES
    private List <ParkingZone> parkingZones;

    //DECLARE CONSTRUCTOR
    public InMemoryParkingZoneRepository(){
        this.parkingZones = new ArrayList<>();
    }

    //DECLARE METHODS
    //SAVE PARKING ZONE
    @Override
    public void save(ParkingZone parkingZone) {
        parkingZones.add(parkingZone);
    }

    //FIND PARKING ZONE
    @Override
    public ParkingZone findByZoneId(String zoneId){

        //LOOP UNTIL CONDITION IS TRUE
        for(ParkingZone parkingZone : parkingZones){

            //CHECK CONDITION
            if (parkingZone.getZoneId().equals(zoneId)) {
                return parkingZone;
            }
        }

        return null;
    }

    //GET ALL PARKING ZONE
    @Override
    public List <ParkingZone> findAll() {
        return parkingZones;
    }
}