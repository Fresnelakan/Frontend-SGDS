package com.sgds.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sgds.demo.Repository.AuthProxy;
import com.sgds.demo.model.Utilisateur;

import lombok.Data;

@Data
@Service
public class AuthService {

    @Autowired
    private AuthProxy authProxy;


    /**
     * Méthode pour inscrire un utilisateur
     */
    public ResponseEntity<?> register(Utilisateur utilisateur) {
        return authProxy.register(utilisateur);
    }


    /**
     * Méthode pour login
     */
    public ResponseEntity<?> login(String mail, String password) {
        return authProxy.loginUtilisateur(mail, password);
    }


    /**
     * Méthode pour récupérer les infos utilisateur
     */
    public ResponseEntity<?> getUserInfos(String token) {
        return authProxy.getUserinfos(token);
    }

    
}
