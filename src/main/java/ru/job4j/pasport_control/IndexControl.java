package ru.job4j.pasport_control;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.04.2021.
 */
@RestController
public class IndexControl {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
