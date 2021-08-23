package org.example.controllers;

import org.example.core.Template;
import org.example.daos.TireuseDetailDao;
import org.example.daos.UserDao;
import org.example.models.Tireuse;
import org.example.utils.URLUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class TireuseDetailController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final TireuseDetailDao tireuseDetailDao = new TireuseDetailDao();



    public String detailfinal(Request request, Response response) {
        Tireuse bierechoisie = detail(request, response);
        Map<String, Object> model = new HashMap<>();
        model.put("biere",bierechoisie);
        return Template.render("tireuse_detail.html", model);

    }



    /** ---------------------------------------------------------------
     * On récupère l'id dans l'URL et on le converti en int.
     * Et on lance une requete SQL pour recup les infos dans la BDD
     * On retourne toutes les caractèristiques de la biere choisie
     * Si l'id n'existe pas, on revient à la page d'acceuil
     */
    public Tireuse detail(Request request, Response response){
        Map<String, String> query = URLUtils.decodeQuery(request.body());

        String id = request.raw().getParameter("id");
        int idTableau = Integer.parseInt(id);

        Tireuse detailbiere = tireuseDetailDao.detailTireuse(idTableau);

        // La biere correspondant à cet id n'a pas été trouvé
        if (detailbiere == null) {
            logger.info("User not found. Redirect to login");
            response.redirect("/fr");
            return null ;
        }

        return detailbiere;

    }

}
