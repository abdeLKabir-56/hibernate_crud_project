# Projet de Gestion des Utilisateurs

Ce projet vise à créer une application web de gestion des utilisateurs en utilisant Java EE, JPA, et Hibernate.

## Structure du Projet

Le projet est divisé en plusieurs packages, chacun avec un rôle spécifique :

- **usermanagement.dao :** Contient la classe UserDao pour l'accès aux données avec Hibernate.
- **usermanagement.model :** Contient la classe User qui représente l'entité User avec JPA.
- **usermanagement.util :** Contient la classe HibernateUtil pour la gestion de la session Hibernate.
- **usermanagement.web :** Contient la servlet UserServlet pour le contrôle des requêtes liées aux utilisateurs.

## Configuration

1. Assurez-vous d'avoir les dépendances Hibernate dans le répertoire "lib".
2. Configurez la base de données dans le fichier `hibernate.cfg.xml`.
3. Déployez l'application sur un serveur compatible Java EE.

## Utilisation

- Accédez à la servlet UserServlet pour gérer les utilisateurs (ajout, modification, suppression).


