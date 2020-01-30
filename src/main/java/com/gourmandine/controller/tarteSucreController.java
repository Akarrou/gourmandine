package com.gourmandine.controller;

import com.gourmandine.entity.FinalProduct;
import com.gourmandine.entity.HalfFinshed;
import com.gourmandine.entity.User;
import com.gourmandine.repository.FinalProductRepository;
import com.gourmandine.repository.HalfFinshedRepository;
import com.gourmandine.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class tarteSucreController {

    @Autowired
    HalfFinshedRepository halfFinshedRepository;

    @Autowired
    FinalProductRepository finalProductRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/tartesSucre")
    public String tartesSucre(Model out) {
        out.addAttribute("halfFinshedFruit", halfFinshedRepository.findAllByType("fruit"));
        out.addAttribute("halfFinshedCreme", halfFinshedRepository.findAllByType("creme"));
        out.addAttribute("halfFinshed", halfFinshedRepository.findAll());
        out.addAttribute("newfinalProduct", new FinalProduct());
        return "tartesSucre";
    }

    @PostMapping("/finalProductSaveClient")
    public String saveFinalproduct(@ModelAttribute FinalProduct finalProduct, @CookieValue(name = "sessionId", required = false) String sessionId) {
        try {
            Optional<User> user = userRepository.findBySession(sessionId);
            if (user.isPresent()) {
                finalProduct.setUser(user.get());
                String code = RandomStringUtils.randomAlphanumeric(8);
                double price = 0;
                for (HalfFinshed h : finalProduct.getHalfFinsheds()) {
                    price += h.getPrices();
                }
                finalProduct.setPrices(price);
                finalProduct.setRefPf(code);
                finalProduct.setStatus("panier");
                finalProductRepository.save(finalProduct);
            }
        } catch (Exception ex) {
        }
        return "redirect:tartesSucre";
    }

    @PostMapping("/commandeClient")
    public String commande(@RequestParam Long id, @CookieValue(name = "sessionId", required = false) String sessionId) {
        try {
            Optional<User> user = userRepository.findBySession(sessionId);
            if (user.isPresent()) {
                Optional<FinalProduct> finalProduct = finalProductRepository.findById(id);
                if (finalProduct.isPresent()) {
                    finalProduct.get().setStatus("commande");
                    finalProductRepository.save(finalProduct.get());
                }
            }
        } catch (Exception ex) {
        }

        return "redirect:tartesSucre";
    }

}
