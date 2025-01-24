package com.sgds.demo.model;
import lombok.Data;

import java.time.LocalDateTime;


@Data

public class Paiement {


    private Long id;
    private Utilisateur utilisateur;
    private Service service;
    private double montant;
    private LocalDateTime datePaiement;
    private TypeStatut typeStatut;

}