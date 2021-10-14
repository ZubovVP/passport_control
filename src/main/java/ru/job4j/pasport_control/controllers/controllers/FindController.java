package ru.job4j.pasport_control.controllers.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource("classpath:global.properties")
@Controller
public class FindController {
    @Autowired
    private RestTemplate rest;
    private  String API;

    @GetMapping("/find")
    public String find(@ModelAttribute Passport passport, Model model) {
        List<Passport> passports;
        if (passport.getId() != 0) {
            passports = new ArrayList<>();
            passports.add(this.rest.getForObject(
                    String.format("%s%s", this.API, "find/{id}"), Passport.class, passport.getId()
            ));
        } else if (passport.getSeria() != 0) {
            passports = this.rest.exchange(
                    String.format("%s/findBySeria?seria=%d", this.API, passport.getSeria()),
                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                    }
            ).getBody();
        } else if (passport.getNumber() != 0) {
            passports = new ArrayList<>();

            passports.add(this.rest.getForObject(
                    String.format("%s%s", this.API, "findByNumber/{number}"), Passport.class, passport.getNumber()
            ));
        } else {
            passports = rest.exchange(
                    String.format("%s%s", this.API, "find"),
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
        passport = this.rest.getForObject(
                String.format("%s%s", this.API, "find/{id}"), Passport.class, id
        );
        return passport.toString();
    }

    @Value("${api-url}")
    public void setAPI(String API) {
        this.API = API;
    }

}
