package ru.job4j.pasport_control.controllers.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Intellij IDEA.
 * User: Vitaly Zubov.
 * Email: Zubov.VP@yandex.ru.
 * Version: $Id$.
 * Date: 27.04.2021.
 */
@Controller
public class IndexControl {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }
}
