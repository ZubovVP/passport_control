package ru.job4j.pasport_control.controllers.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.pasport_control.service.PassportService;


/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 03.05.2021.
 */
@RestController
@RequestMapping("/passport")
public class DeleteRestController {
    @Autowired
    private PassportService ps;

    @PostMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody int id) {
        this.ps.delete(id);
        return ResponseEntity.ok().build();
    }

}
