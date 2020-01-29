package com.gourmandine.controller;


import com.gourmandine.entity.FinalProduct;
import com.gourmandine.repository.CommandeRepository;
import com.gourmandine.repository.FinalProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FinalProductController {
    @Autowired
    FinalProductRepository FinalProductRepository;
    @Autowired
    CommandeRepository commandeRepository;

    @GetMapping("/adminFinalProduct")
    public String adminFinalProduct(Model out) {
        out.addAttribute("newfinalProduct", new FinalProduct());
        out.addAttribute("finalProduct", FinalProductRepository.findAll());
        return "adminFinalProduct";
    }

    @PostMapping("/finalProductSave")
    public String saveFinalproduct(@ModelAttribute FinalProduct finalProduct) {
        try{
            FinalProductRepository.save(finalProduct);
        }catch (Exception ex){
        }

        return "redirect:adminFinalProduct";
    }
}
