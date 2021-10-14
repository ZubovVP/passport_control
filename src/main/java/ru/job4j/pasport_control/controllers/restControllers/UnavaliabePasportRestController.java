package ru.job4j.pasport_control.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.pasport_control.models.Passport;
import ru.job4j.pasport_control.service.PassportService;

import java.util.List;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 04.05.2021.
 */
@RestController
@RequestMapping("/passport")
public class UnavaliabePasportRestController {
    @Autowired
    private PassportService ps;

    @GetMapping("/unavaliabe")
    private List<Passport> findAll() {
        return this.ps.findByUnavaliabe();
    }
}
