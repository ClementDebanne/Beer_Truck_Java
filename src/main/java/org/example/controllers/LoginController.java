package org.example.controllers;

import org.example.core.Conf;
import org.example.core.Template;
import org.example.daos.UserDao;
import org.example.models.User;
import org.example.utils.SessionUtils;
import org.example.utils.URLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    // Création d'une instance de UserDao qui pourra être utilisée dans toutes les fonctions
    // du controleur.
    private final UserDao userDao = new UserDao();

    public String displayLogin(Request request, Response response) {
         String myusername = "ROBIN";
        // String myusername = User.username;
        Map<String, Object> model = new HashMap<>();
        model.put("username", myusername);
        return Template.render("login.html", model);
    }


    public String contact(Request req, Response res){
        Map<String, Object> model = new HashMap<>();
        return Template.render("contact.html",model);
    }







    /**
     * Permet de créer une session pour un utilisateur qui veut se connecter.
     * L'objectif est de retrouver les identifiants fourni dans la BDD.
     */
    public String authenticate(Request request, Response response) {
        // On récupère les données du formulaire qui ont été envoyées par la page de login
        // ici chaque clé ("username" / "password") correspond à l'attribut name précisé
        // dans les inputs du form
        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String username = query.get("username");
        String password = query.get("password");

        // On essaye de récupérer l'utilisateur associé à ces identifiants
        User user = userDao.getUserByCredentials(username, password);

        System.out.println(username + password);

        // L'utilisateur correspondant à ces identifiants n'a pas été trouvé
        if (user == null) {
            logger.info("User not found. Redirect to login");
            response.removeCookie("session");
            response.redirect("/login");
            return "KO";
        }

        // Création d'une session utilisateur
        SessionUtils.createSession(user, request, response);
        return null;
    }













    /**
     * Permet de créer un nouvel utilisateur dans le cadre d'une inscription.
     * La page HTML aura soumis un formulaire (HTTP POST) avec les informations de l'utilisateur
     * et cette fonction se charge de transmettre les infos à
     */
    public String signUp(Request request, Response response) {
        // On peut vérifier la méthode HTTP qui a été utilisée pour accéder
        // a cette fonction. Si c'est un GET, c'est qu'il n'y a pas de formulaire POST à traiter
        if (request.requestMethod().equals("GET")) {
            Map<String, Object> model = new HashMap<>();
            return Template.render("auth_signup.html", model);
        }

        // Récupération des infos de la requete POST
        // On lit les informations du formulaire posté

        Map<String, String> query = URLUtils.decodeQuery(request.body());
        String username = query.get("username");
        String password = query.get("password");
        String mail = query.get("Email Address");
        String name = query.get("First Name");
        String surname = query.get("Last Name");

        System.out.println(username + password + mail + name + surname);


        // On crée l'utilisateur !
        int newId = userDao.createUser(username, password, mail, name, surname);
        if (newId <= 0) {
            // probleme !!!
            // on a pas réussi a créer l'utliisateur
        }

        // Si tout s'est bien passé, on redirige l'utilisateur vers la page de login
        response.redirect("/login");
        return null;
    }
}
