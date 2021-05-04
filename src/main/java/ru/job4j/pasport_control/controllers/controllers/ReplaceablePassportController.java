package ru.job4j.pasport_control.controllers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import ru.job4j.pasport_control.models.Passport;

import java.util.List;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.05.2021.
 */
@Controller
public class ReplaceablePassportController {
    @Autowired
    private RestTemplate rest;

    @GetMapping("/find-replaceable")
    public String getUnavaliabePasports(Model model) {
        List<Passport> passports = rest.exchange(
                "http://localhost:8080/passport/find-replaceable",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        model.addAttribute("result", passports);
        return "index";
    }
}
