package com.jobs.workbook.controlllers;

import com.jobs.workbook.entites.location.GeoLocation;
import com.jobs.workbook.repositories.GeoLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LocationController {

    @Autowired
    private GeoLocationRepository geoLocationRepository;


    @PutMapping("/jpa/location/update")
    public ResponseEntity<Object> updateLocation(@RequestBody GeoLocation locationToUpdate) {
        GeoLocation location = geoLocationRepository.findOneById(locationToUpdate.getId());
        if (location != null) {
            location.setLatitude(locationToUpdate.getLatitude());
            location.setLongitude(locationToUpdate.getLongitude());
            location.setAddress(locationToUpdate.getAddress());

            geoLocationRepository.save(location);
            return new ResponseEntity<>(location, HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(locationToUpdate, HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/jpa/location/register")
    public ResponseEntity<GeoLocation> addLocation(@RequestBody GeoLocation location) {
        return new ResponseEntity<>(this.geoLocationRepository.save(location), HttpStatus.CREATED);
    }
}
