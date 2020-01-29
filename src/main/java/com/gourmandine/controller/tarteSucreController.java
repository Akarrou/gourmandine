package com.gourmandine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tarteSucreController {

    @GetMapping("/tartesSucre")
    public String tartesSucre(Model out) {
        return "tartesSucre";
    }
}
