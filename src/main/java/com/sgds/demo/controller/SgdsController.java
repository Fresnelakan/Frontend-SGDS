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

    @GetMapping("/login")
    public String login(Model model, @RequestParam(required = false) String redirectUrl) {
        Utilisateur utilisateur = new Utilisateur();
        model.addAttribute("utilisateur", utilisateur);
        model.addAttribute("redirectUrl", redirectUrl);
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String motDePasse, 
                        @RequestParam(required = false) String redirectUrl, Model model, 
                        HttpServletResponse httpServletResponse) {
        var response = authProxy.loginUtilisateur(email, motDePasse);
        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            model.addAttribute("error", "Nom d'utilisateur ou mot de passe incorrect");
            return "login";
        }
        @SuppressWarnings("unchecked")
        Map<String, Object> body = (Map<String, Object>) response.getBody();
        @SuppressWarnings("null")
        String token = (String) body.get("token");
        Cookie authCookie = new Cookie("authToken", token);
        authCookie.setHttpOnly(true);
        authCookie.setPath("/");
        authCookie.setMaxAge(24 * 60 * 60);
        httpServletResponse.addCookie(authCookie);
        return "redirect:" + (redirectUrl != null ? redirectUrl : "/");
    }

    @GetMapping("/testimonial")
    public String testimonial() {
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
        // Ajoutez ici la logique pour sauvegarder l'utilisateur (ex: base de données)
        return "redirect:/login"; 
    }

}
