package ru.job4j.pasport_control.controllers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;
import ru.job4j.pasport_control.models.Passport;

import java.util.List;


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
    private String API;

    @PostMapping({"/save", "/update"})
    public String create(@RequestBody Passport passport, Model model) {
        Passport rsl = rest.postForObject(String.format("%s%s", this.API, "/save"), passport, Passport.class);
        List<Passport> passports = this.rest.exchange(
                String.format("%s%s", this.API, "find"),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Passport>>() {
                }
        ).getBody();
        model.addAttribute("result", passports);
        return "index";
    }

    @Value("${api-url}")
    public void setAPI(String API) {
        this.API = API;
    }
}
