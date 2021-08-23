package org.example.controllers;

import org.example.core.Template;
import org.example.daos.TireuseDao;
import org.example.models.Tireuse;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TireuseController {

    private final TireuseDao tireuseDao = new TireuseDao();

    public String home(Request request, Response response){
        List<Tireuse> listetireuses = tireuseDao.getTireuses();
        Map<String, Object> model = new HashMap<>();
        model.put("liste",listetireuses);
        return Template.render("home.html", model);
    }

}



