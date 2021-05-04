package ru.job4j.pasport_control.controllers.restControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
public class FindRestController {
    @Autowired
    private PassportService ps;

    @GetMapping("/find")
    private List<Passport> findAll() {
        return this.ps.findAll();
    }

    @GetMapping("/find/{id}")
    public Passport findById(@PathVariable int id) {
        return this.ps.findById(id);
    }

    @GetMapping("/findBySeria")
    public List<Passport> findBySeria(@RequestParam int seria) {
        return this.ps.findBySeria(seria);
    }

    @GetMapping("/findByNumber/{number}")
    public Passport findByNumber(@PathVariable int number) {
        return this.ps.findByNumber(number);
    }
}

