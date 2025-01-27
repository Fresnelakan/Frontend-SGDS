package com.sgds.demo.model;

import jakarta.persistence.Entity;
import lombok.Data;


@Entity

@Data
public class Utilisateur {

    
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;

    private Role role;

    private Double latitude;
    private Double longitude;


    public enum Role {
        SOUSCRIPTEUR, AGENT;
    }

    
}
