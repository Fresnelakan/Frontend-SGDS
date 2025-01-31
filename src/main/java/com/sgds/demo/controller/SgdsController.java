package com.sgds.demo.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.sgds.demo.Repository.AuthProxy;
import com.sgds.demo.Service.AuthService;
import com.sgds.demo.model.Utilisateur;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class SgdsController {

    @Autowired
    private AuthProxy authProxy;

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String redirectUrl) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }

    @SuppressWarnings({ "unchecked", "null" })
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String motDePasse, 
                        Model model, 
                        HttpServletResponse httpServletResponse) {
        var response = authProxy.loginUtilisateur(email, motDePasse);
        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login";
        }
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        String token = (String) body.get("token");
        Cookie authCookie = new Cookie("authToken", token);
        authCookie.setHttpOnly(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(24 * 60 * 60);
        httpServletResponse.addCookie(authCookie);

        ResponseEntity<?> userInfosResponse = authService.getUserInfos(token);

        if (!userInfosResponse.getStatusCode().is2xxSuccessful()) {
            return "redirect:/login";
        }

        Utilisateur utilisateur = (Utilisateur) userInfosResponse.getBody();

        if (utilisateur.getRole() == Utilisateur.Role.SOUSCRIPTEUR) {
            return "redirect:/404";
        } else if(utilisateur.getRole() == Utilisateur.Role.AGENT) {
            return "redirect:/testimonial";
        } else {
            return "login";
        }

        
    }

    @GetMapping("/testimonial")
    public String testimonial(@CookieValue(name = "authToken") String token, Model model) {
        if(token.isEmpty()) {
            return "redirect:/login";
        }
        return "testimonial";
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }

    @GetMapping("/register")
    public String register(Model model) {
    model.addAttribute("utilisateur", new Utilisateur());
    return "register";
    }

   @PostMapping("/register")
    public String registerUser(@ModelAttribute Utilisateur utilisateur, Model model) {
        // AJOUTEZ CE CODE POUR SAUVEGARDER L'UTILISATEUR
        ResponseEntity<?> response = authProxy.register(utilisateur);
        if (response.getStatusCode().is2xxSuccessful()) {
            return "redirect:/login";
        } else {
            model.addAttribute("error", "Erreur lors de l'inscription");
            return "register";
        }
    }

}