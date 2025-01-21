package com.sgds.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgds.demo.Service.LocationService;
import com.sgds.demo.model.Location;

@Controller
public class SgdsController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/testimonial")
    public String testimonial() {
        return "testimonial ";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/404")
    public String affiche404() {
        return "404 ";
    }


    @RestController
     public class LocationController {

      @Autowired private LocationService locationService;  
   @PostMapping("/location")
    public String receiveLocation(@RequestBody Map<String, Object> locationData) { 
        double latitude = (double) locationData.get("latitude");
         double longitude = (double) locationData.get("longitude");
          double accuracy = (double) locationData.get("accuracy"); 

          // Traitement des données de géolocalisation ici 
          Location location = new Location(); 
          location.setLatitude(latitude);
           location.setLongitude(longitude);
            location.setAccuracy(accuracy);
             locationService.saveLocation(location); return "Location received and saved"; }
        } 
    }

