package com.sgds.demo.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Notification {

   
    private Long id;

    private Utilisateur utilisateur;

    private String message;

    private LocalDateTime dateNotification;

}



