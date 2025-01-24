package com.sgds.demo.model;

import lombok.Data;

@Data
public class Vehicule {

    private Long id;

    private int capacite;

    public enum Type {
        CLOBOTO, CAMION_A_BENNE
    }

    
}
