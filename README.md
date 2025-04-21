# Documentation du Projet d'Optimisation des Trajets des Collecteurs de Déchets de SGDS

## 1. Introduction
Ce projet vise à optimiser les trajets des collecteurs de déchets en utilisant l'API de routage GraphHopper et OpenStreetMap. 
L'objectif est d'améliorer l'efficacité des collectes en proposant un itinéraire optimal aux agents collecteurs 
tout en permettant aux souscripteurs de mettre à jour leur localisation en temps réel.

## 2. Architecture du Projet
- **Backend** : Spring Boot (Java 21), MySQL
- **Frontend** : Template HTML/CSS intégré à Spring Boot
- **Cartographie** : Leaflet.js pour l'affichage des cartes
- **Optimisation des trajets** : API GraphHopper (15 000 requêtes gratuites/jour)
- **Authentification** : JWT

## 3. API et Fonctionnalités
### Endpoints principaux
- **Authentification**
  - `POST http://localhost:8080/api/Auth/register` : Inscription des utilisateurs
  - `POST http://localhost:8080/api/Auth/login` : Connexion des utilisateurs
- **Gestion des trajets**
  - `POST http://localhost:8080/api/trajets/optimiser` : Optimisation des trajets avec GraphHopper
- **Affichage des utilisateurs**
  - `GET http://localhost:8080/api/utilisateurs/client` : Affichage des utilisateurs sur la carte Leaflet

## 4. Technologies Utilisées
- **Backend** : Spring Boot (Java 21), JWT pour l'authentification
- **Base de données** : MySQL
- **Cartographie** : OpenStreetMap via Leaflet.js
- **Optimisation des trajets** : GraphHopper
- **Frontend** : Template HTML/CSS intégré à Spring Boot

## 5. Déploiement et Hébergement
- **Actuellement en local**
- **Pas de conteneurisation Docker utilisée**

## 6. Prérequis
- **Java 21+** (Java 23 installé, mais Java 21 défini dans le projet)
- **Maven**
- **MySQL**
- **WAMP/XAMPP** (avec accès à phpMyAdmin)

## 7. Workflow Utilisateur
### Étapes préliminaires :
1. **Démarrer MySQL** :
   - Lancer WAMP/XAMPP et démarrer le service MySQL
   - Vérifier qu'il utilise le port `3306` (si problème avec XAMPP, privilégier WAMP)

2. **Base de données** :
   - Accéder à phpMyAdmin (`http://localhost/phpmyadmin`)
   - Créer une base de données nommée **`sgds`** (respecter la casse)
   - Importer le fichier `sgds.sql` fourni dans cette base

### Configuration des projets :
3. **Ouvrir les dossiers dans VS Code** :
   - Ouvrir `Frontend-SGDS` (frontend) et `SpringBoot---SGDS` (backend) dans deux fenêtres vs code  séparées
   - Attendre que VS Code termine le chargement des extensions (vérifier la notification "JAVA READY" pour le backend et le frontend )

4. **Lancer le Backend** :
   - Dans le terminal du projet backend (`Ctrl + ù` pour ouvrir) :
   ```lancer la commande
   mvn spring-boot:run
   S'assurer que le serveur tomcat est démarrer sur le  port 8080

5. **Lancer le Frontend (Après lancement du Backend)** :
   - Dans le terminal du projet Frontend (`Ctrl + ù` pour ouvrir) :
   ```lancer la commande 
   mvn spring-boot:run
   S'assurer que le serveur tomcat est démarrer sur le  port 9000

6. **Accès au projet** : Ouvrir `http://localhost:9000` dans un navigateur (chrome est très recommandé - edge supporte mal l'optimisation)
7. **Navigation** : En haut de la page, une barre de navigation permet d’accéder aux pages :
   - **Souscripteur**
   - **Client**
8. **Pour les souscripteurs** :
   - Connexion avec identifiants
   - Accès à la page testimonial (avec la carte affichant les utilisateurs de la base de données)
   - Bouton "Optimiser le trajet" pour afficher un itinéraire optimisé
9. **Pour les clients** :
   - Connexion avec identifiants
   - Page avec une carte permettant d’actualiser la position en envoyant une requête pour mettre à jour la localisation
   - Envoi de notifications (fonctionnalité non encore implémentée)

## 8. Contributeurs
- **Fresnel AKAN** (Chef de projet) : Configuration du backend, intégration des fonctionnalités, amélioration des pages souscripteurs, clients et authentification.
- **Marzou Marzouk & SALANON Bill** : Amélioration de la page d’accueil du frontend.

## 9. Prochaines Améliorations
- Implémentation de la fonctionnalité d’envoi de notifications.
---


