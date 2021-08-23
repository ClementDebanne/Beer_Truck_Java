package org.example;

import org.example.controllers.ContactController;
import org.example.controllers.LoginController;
import org.example.controllers.TireuseController;
import org.example.controllers.TireuseDetailController;
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



        // Spark.get("/fr", (req, res) -> {
        //    return Template.render("home.html", new HashMap<>());
        // });

        TireuseController tireuseController = new TireuseController();
        LoginController loginController = new LoginController();
        ContactController contactController = new ContactController();
        TireuseDetailController tireuseDetailController = new TireuseDetailController();


        Spark.get("/fr",((req, res) -> tireuseController.home(req,res)));

        Spark.get("/login", (req, res) -> loginController.displayLogin(req, res));

        Spark.post("/login", (req, res) -> loginController.authenticate(req, res));

        Spark.get("/signup", (req, res) -> loginController.signUp(req, res));

        Spark.post("/signup", (req, res) -> loginController.signUp(req, res));

        Spark.get("/contact", (req, res) -> contactController.contactPage(req,res));

        Spark.get("/tireuses",((req,res) -> tireuseDetailController.detailfinal(req, res)));

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
