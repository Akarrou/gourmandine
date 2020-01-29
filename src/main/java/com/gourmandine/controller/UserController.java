package com.gourmandine.controller;

import com.gourmandine.entity.User;
import com.gourmandine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @GetMapping("/inscription")
    public String getAll(Model out ){
        out.addAttribute("newuser", new User());
        return "/inscription";
    }

    @PostMapping("/usersave")
    public String userSave(@ModelAttribute User user) {
            userRepository.save(user);
        return "redirect:/connexion";
    }
}

