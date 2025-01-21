package com.sgds.demo.Service;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.sgds.demo.Repository.LocationRepository;
import com.sgds.demo.model.Location;

import java.util.List; 

@Service
 public class LocationService {
    
    @Autowired 
    private LocationRepository locationRepository;

     public Location saveLocation(Location location) { 
        return locationRepository.save(location);
     } 

     public List<Location> getAllLocations() { 
        return locationRepository.findAll(); 
    }
     }