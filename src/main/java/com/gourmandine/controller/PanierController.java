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
            out.addAttribute("enCommande",finalProductRepository.findAllByUserAndStatus(user.get(),"commande") );
        }
        return "/panier";
    }

    @PostMapping("/deleteCommandeClient")
    public String deleteCommandeClient(@RequestParam Long id, @CookieValue(name = "sessionId", required = false) String sessionId) {
        try {
            Optional<User> user = userRepository.findBySession(sessionId);
            if (user.isPresent()) {
                Optional<FinalProduct> finalProduct = finalProductRepository.findById(id);
                if (finalProduct.isPresent()) {

                    finalProductRepository.delete(finalProduct.get());
                }
            }
        } catch (Exception ex) {
        }

        return "redirect:panier";
    }
}
