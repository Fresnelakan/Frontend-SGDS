package com.sgds.demo.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sgds.demo.Repository.AuthProxy;
import com.sgds.demo.model.Utilisateur;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class SgdsController {

    @Autowired
    private AuthProxy authProxy;

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


    @GetMapping("/register")
    public String register(Model model) {
        Utilisateur utilisateur = new Utilisateur();

        model.addAttribute("utilisateur", utilisateur);

        return "register";
    }


    @PostMapping("/register") 
    public String saveUtilisateur(@ModelAttribute Utilisateur utilisateur, RedirectAttributes redirectAttributes) {
        var response = authProxy.register(utilisateur);

        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/login";
        }else {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de l'inscription : " + response.getBody());
            return "redirect:/register";
        }
    }


    @GetMapping("/login")
    public String login(Model model) {
        Utilisateur utilisateur = new Utilisateur();

        model.addAttribute("utilisateur", utilisateur);
        return "login";
    }


    @SuppressWarnings({ "unchecked", "null" })
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String motDePasse, Model model, HttpServletResponse httpServletResponse) {
        var response = authProxy.loginUtilisateur(email, motDePasse);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login";
        }

        Map<String, Object> body = (Map<String, Object>) response.getBody();
        
        String token = (String) body.get("token");

        // Stocker le token dans un cookie
        Cookie authCookie = new Cookie("authToken", token);
        authCookie.setHttpOnly(true); // Protéger contre les scripts
        authCookie.setPath("/"); // Disponible pour toutes les pages
        authCookie.setMaxAge(24 * 60 * 60); // Durée de vie de 24 heures (en secondes)
        httpServletResponse.addCookie(authCookie);

        return "accueil";

        // Envoyer le token au backend pour obtenir le username
        /* ResponseEntity<?> userInfoResponse = etudiantService.getUserInfo(token);

        if (userInfoResponse.getStatusCode().is2xxSuccessful()) {
            String usernameFromApi = (String) userInfoResponse.getBody();
            ResponseEntity<?> allInfoResponse = etudiantService.getAllEtdInfo(token);
            User etudiant = (User) allInfoResponse.getBody();

            if ("Etudiant".equals(etudiant.getRole())) {
                model.addAttribute("username", usernameFromApi);
                return "accueil_etd";
            }
            return "redirect:/logout_etd";
        }
        else {
            model.addAttribute("error", "Erreur lors de la récupération des informations utilisateur");
            return "login_etd";
        } */
    }


    
}

