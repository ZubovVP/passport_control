package ru.job4j.pasport_control.controllers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.05.2021.
 */
@Controller
public class DeleteController {
    @Autowired
    private RestTemplate rest;
    private static final String API = "http://localhost:8080/passport/delete";

    @PostMapping("/delete")
    public String delete(@RequestParam int id) {
        this.rest.postForObject(API, id, Integer.class);
        return "index";
    }
}
