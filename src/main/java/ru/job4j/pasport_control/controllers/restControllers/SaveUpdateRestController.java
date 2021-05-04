package ru.job4j.pasport_control.controllers.restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.pasport_control.models.Passport;
import ru.job4j.pasport_control.service.PassportService;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 02.05.2021.
 */
@RestController
@RequestMapping("/passport")
public class SaveUpdateRestController {
    private final PassportService service;

    public SaveUpdateRestController(PassportService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity<Passport> saveOrUpdate(@RequestBody Passport passport) {
        return new ResponseEntity<>(
                this.service.save(passport),
                HttpStatus.CREATED
        );
    }

}
