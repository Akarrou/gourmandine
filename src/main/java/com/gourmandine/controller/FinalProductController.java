package com.gourmandine.controller;


import com.gourmandine.entity.FinalProduct;
import com.gourmandine.repository.CommandeRepository;
import com.gourmandine.repository.FinalProductRepository;
import com.gourmandine.repository.HalfFinshedRepository;
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
    HalfFinshedRepository halfFinshedRepository;

    @GetMapping("/adminFinalProduct")
    public String adminFinalProduct(Model out) {
        out.addAttribute("newfinalProduct", new FinalProduct());
        out.addAttribute("finalProduct", FinalProductRepository.findAll());
        out.addAttribute("halfFinshed", halfFinshedRepository.findAll());
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
