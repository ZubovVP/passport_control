package ru.job4j.pasport_control.controllers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import ru.job4j.pasport_control.models.Passport;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.05.2021.
 */
@Controller
public class SaveUpdateController {
    @Autowired
    private RestTemplate rest;
    private static final String API = "http://localhost:8080/passport/save";

    @PostMapping("/save")
    public String create(@RequestBody Passport passport) {
        Passport rsl = rest.postForObject(API, passport, Passport.class);
        return "index";
    }

    @PostMapping("/update")
    public String update(@RequestBody Passport passport) {
        Passport rsl = rest.postForObject(API, passport, Passport.class);
        return "index";
    }

}
