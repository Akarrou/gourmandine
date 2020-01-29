package com.gourmandine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class home {

    @GetMapping("/")
    public String home(Model out) {

        return "index";
    }
}
