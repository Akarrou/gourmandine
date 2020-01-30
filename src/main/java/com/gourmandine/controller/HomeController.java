package com.gourmandine.controller;

import com.gourmandine.entity.FinalProduct;
import com.gourmandine.entity.User;
import com.gourmandine.repository.FinalProductRepository;
import com.gourmandine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class HomeController {

    @Autowired
    FinalProductRepository finalProductRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/")
    public String home(Model out) {
        out.addAttribute("tarte", finalProductRepository.findAllByStatus("vente"));
        return "index";
    }

    @PostMapping("/ajoutePanier")
    public String ajoutePanier(@RequestParam Long id, @CookieValue(name = "sessionId", required = false) String sessionId) {
        try {
            Optional<User> user = userRepository.findBySession(sessionId);
            if (user.isPresent()) {
                Optional<FinalProduct> finalProduct = finalProductRepository.findById(id);
                if (finalProduct.isPresent()) {
                    finalProduct.get().setUser(user.get());
                    finalProduct.get().setStatus("panier");
                    finalProductRepository.save(finalProduct.get());
                }
            }
        } catch (Exception ex) {
        }

        return "redirect:tartesSucre";
    }
}
