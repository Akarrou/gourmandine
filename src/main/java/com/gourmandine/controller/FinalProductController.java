package com.gourmandine.controller;

import com.gourmandine.entity.FinalProduct;
import com.gourmandine.entity.User;
import com.gourmandine.repository.FinalProductRepository;
import com.gourmandine.repository.HalfFinshedRepository;
import com.gourmandine.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Controller
public class FinalProductController {

    @Autowired
    FinalProductRepository finalProductRepository;

    @Autowired
    HalfFinshedRepository halfFinshedRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/adminFinalProduct")
    public String adminFinalProduct(Model out) {
        out.addAttribute("newfinalProduct", new FinalProduct());
        out.addAttribute("finalProduct", finalProductRepository.findAll());
        out.addAttribute("halfFinshed", halfFinshedRepository.findAll());
        return "adminFinalProduct";
    }

    @PostMapping("/finalProductSave")
    public String saveFinalproduct(@ModelAttribute FinalProduct finalProduct, @RequestParam(required = false) MultipartFile picture,
                                   @CookieValue(name = "sessionId", required = false) String sessionId) {
        try{
            Optional<User> user = userRepository.findBySession(sessionId);
            if (user.isPresent()) {
                String code = RandomStringUtils.randomAlphanumeric(8);
                finalProduct.setUser(user.get());
                finalProduct.setStatus("vente");
                finalProduct.setRefPf(code);
                finalProductRepository.save(finalProduct);
            }
        }catch (Exception ex){
        }
        return "redirect:adminFinalProduct";
    }

    @PostMapping("/tarteProduite")
    public String tarteProduite(@RequestParam Long id, @CookieValue(name = "sessionId", required = false) String sessionId) {
        try {
            Optional<User> user = userRepository.findBySession(sessionId);
            if (user.isPresent()) {
                Optional<FinalProduct> finalProduct = finalProductRepository.findById(id);
                if (finalProduct.isPresent()) {
                    finalProduct.get().setStatus("fabrique");
                    finalProductRepository.saveAndFlush(finalProduct.get());
                }
            }
        } catch (Exception ex) {
        }

        return "redirect:adminFinalProduct";
    }

    @PostMapping("/tarteLivrer")
    public String tarteLivrer(@RequestParam Long id, @CookieValue(name = "sessionId", required = false) String sessionId) {
        try {
            Optional<User> user = userRepository.findBySession(sessionId);
            if (user.isPresent()) {
                Optional<FinalProduct> finalProduct = finalProductRepository.findById(id);
                if (finalProduct.isPresent()) {
                    finalProduct.get().setStatus("livree");
                    finalProductRepository.saveAndFlush(finalProduct.get());
                }
            }
        } catch (Exception ex) {
        }
        return "redirect:adminFinalProduct";
    }
}
