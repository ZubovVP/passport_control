package ru.job4j.pasport_control.controllers.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import ru.job4j.pasport_control.models.Passport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.05.2021.
 */
@Controller
public class FindController {
    @Autowired
    private RestTemplate rest;
    private static final String API = "http://localhost:8080/passport/find";
    private static final String API_ID = "http://localhost:8080/passport/find/{id}";
    private static final String API_NUMBER = "http://localhost:8080/passport/findByNumber/{number}";

    @GetMapping("/find")
    public String find(@ModelAttribute Passport passport, Model model) {
        List<Passport> passports;
        if (passport.getId() != 0) {
            passports = new ArrayList<>();
            passports.add(rest.getForObject(
                    API_ID, Passport.class, passport.getId()
            ));
        } else if (passport.getSeria() != 0) {
            passports = rest.exchange(
                    String.format("http://localhost:8080/passport/findBySeria?seria=%d", passport.getSeria()),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                    }
            ).getBody();
        } else if (passport.getNumber() != 0) {
            passports = new ArrayList<>();

            passports.add(rest.getForObject(
                    API_NUMBER, Passport.class, passport.getNumber()
            ));
        } else {
            passports = rest.exchange(
                    API,
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                    }
            ).getBody();
        }
        model.addAttribute("result", passports);
        return "index";
    }

    @GetMapping("/find/{id}")
    public String findById(@PathVariable int id) {
        Passport passport;
        passport = rest.getForObject(
                API_ID, Passport.class, id
        );
        return passport.toString();
    }

}
