package com.gourmandine.controller;

import com.gourmandine.entity.User;
import com.gourmandine.repository.FinalProductRepository;
import com.gourmandine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller()
public class PanierController {
    @Autowired
    FinalProductRepository finalProductRepository;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/panier")
    public String panier(Model out, @CookieValue(name = "sessionId", required = false) String sessionId) {
        Optional<User> user = userRepository.findBySession(sessionId);
        if (user.isPresent()) {
            out.addAttribute("panier",finalProductRepository.findAllByUserAndStatus(user.get(),"panier") );
            out.addAttribute("somme",finalProductRepository.findAllByUserAndStatus(user.get(),"panier") );
        }
        return "/panier";
    }
}
