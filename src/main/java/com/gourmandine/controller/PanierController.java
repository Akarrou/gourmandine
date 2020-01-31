package com.gourmandine.controller;

import com.gourmandine.entity.Commande;
import com.gourmandine.entity.FinalProduct;
import com.gourmandine.entity.User;
import com.gourmandine.repository.CommandeRepository;
import com.gourmandine.repository.FinalProductRepository;
import com.gourmandine.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

@Controller()
public class PanierController {
    @Autowired
    FinalProductRepository finalProductRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommandeRepository commandeRepository;

    @GetMapping("/panier")
    public String panier(Model out, @CookieValue(name = "sessionId", required = false) String sessionId) {
        Optional<User> user = userRepository.findBySession(sessionId);
        if (user.isPresent()) {
            out.addAttribute("panier", finalProductRepository.findAllByUserAndStatus(user.get(), "panier"));
            out.addAttribute("enCommande", finalProductRepository.findAllByUserAndStatus(user.get(), "commande"));
            out.addAttribute("enlivraison", finalProductRepository.findAllByUserAndStatus(user.get(), "fabrique"));
            out.addAttribute("commande", new Commande());
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

    @PostMapping("/commandeClient")
    public String commande(@ModelAttribute Commande commande, @CookieValue(name = "sessionId", required = false) String sessionId) {
        try {
            Optional<User> user = userRepository.findBySession(sessionId);
            if (user.isPresent()) {
                Optional<FinalProduct> finalProduct = finalProductRepository.findById(commande.getFinalProduct().getIdFp());
                if (finalProduct.isPresent()) {
                    finalProduct.get().setStatus("commande");
                    finalProductRepository.save(finalProduct.get());
                }
                commande.setClient(user.get());
                LocalDate today = LocalDate.now();
                commande.setDate(today);
                String code = RandomStringUtils.randomAlphanumeric(8);
                commande.setRefOrder(code);
                commandeRepository.save(commande);
            }
        } catch (Exception ex) {
        }
        return "redirect:panier";
    }

}
