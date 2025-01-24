package com.sgds.demo.Repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.sgds.demo.CustomProperties;
import com.sgds.demo.model.Utilisateur;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AuthProxy {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CustomProperties customProperties;

    public ResponseEntity<?> register(Utilisateur utilisateur) {
        String url = customProperties.getApiUrl() + "/api/Auth/register";
        log.info("Sending POST request to: " + url);
        try {
            ResponseEntity<Utilisateur> response = restTemplate.postForEntity(url, utilisateur, Utilisateur.class);
            log.info("Inscription réussie pour l'utilisateur: {}", utilisateur.getNom());
            return response;
        } catch(HttpClientErrorException e) {
            log.error("Erreur lors de l'inscription: {}", e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (Exception e) {
            log.error("Erreur lors de l'inscription: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur inattendue lors de l'inscription");
        }
        
    }

    public ResponseEntity<?> loginUtilisateur(String usermail, String password) {
        String loginUrl = customProperties.getApiUrl() + "/api/Auth/login";

        // Créer une instance d'étudiant pour représenter les credentials.
        Utilisateur credentials = new Utilisateur();
        credentials.setEmail(usermail);
        credentials.setMotDePasse(password);

        // Appeler l'API et retourner la réponse.
        try {
            ResponseEntity<?> response = restTemplate.postForEntity(loginUrl, credentials, Map.class); // Map.class pour capturer les données de type {token, type}
            log.info("Login réussi pour l'utilisateur: {}", usermail);
            return response;
        } catch (Exception e) {
            log.error("Erreur de connexion pour l'utilisateur {}: {}", usermail, e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
}
