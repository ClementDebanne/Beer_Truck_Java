package org.example.controllers;

import org.example.core.Template;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class LoginController {
    public String displayLogin(Request request, Response response){
        String myusername = "ROBIN";
        Map<String, Object> model=new HashMap<>();
        model.put("username", myusername);
        return Template.render("login.html", model);
    }
}
