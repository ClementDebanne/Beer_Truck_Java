package org.example;

import org.example.controllers.LoginController;
import org.example.core.Conf;
import org.example.core.Database;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import spark.Spark;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        initialize();

        // Instancier classe LoginController pour avoir un objet
        // Classe = ADN
        // Objet = Cellule


        // Spark.get("/acceuil", (req, res) -> {
        // A mettre quand security.html fait


        Spark.get("/fr", (req, res) -> {
            return Template.render("home.html", new HashMap<>());
        });

        LoginController loginController = new LoginController();

        Spark.get("/contact", (req, res) -> loginController.contact(req,res));

        Spark.get("/login", (req, res) -> loginController.displayLogin(req, res));

        Spark.post("/login", (req, res) -> loginController.authenticate(req, res));

        Spark.get("/signup", (req, res) -> loginController.signUp(req, res));

        Spark.post("/signup", (req, res) -> loginController.signUp(req, res));



    }

    static void initialize() {
        Template.initialize();

        Database.get().checkConnection();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);
        final LoggerMiddleware log = new LoggerMiddleware();
        Spark.before(log::process);
    }
}
