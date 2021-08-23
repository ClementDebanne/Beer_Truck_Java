package org.example.controllers;

import org.example.core.Template;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class ContactController {

    public String contactPage(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();
        return Template.render("contact.html",model);
    }

}
