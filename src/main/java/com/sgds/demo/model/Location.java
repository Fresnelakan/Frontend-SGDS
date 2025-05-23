package com.sgds.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
      private double latitude;
      private double longitude; 
      private double accuracy;
        // Getters et Setters 
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
          public double getLatitude() { return latitude; }
          public void setLatitude(double latitude) { this.latitude = latitude; }
            public double getLongitude() { return longitude; }
            public void setLongitude(double longitude) { this.longitude = longitude; } 
              public double getAccuracy() { return accuracy; } 
              public void setAccuracy(double accuracy) { this.accuracy = accuracy; }
}
