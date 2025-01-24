package com.sgds.demo.model;

import lombok.Data;




@Data
public class Utilisateur {

    
    private Long id;

    private String nom;
    private String email;
    private String motDePasse;

    private Role role;


    private Adresse adresse;

    public enum Role {
        SOUSCRIPTEUR, AGENT;
    }

    
}
