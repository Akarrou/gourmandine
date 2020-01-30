package com.gourmandine.controller;

import com.gourmandine.entity.FinalProduct;
import com.gourmandine.repository.FinalProductRepository;
import com.gourmandine.repository.HalfFinshedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class tarteSucreController {

    @Autowired
    HalfFinshedRepository halfFinshedRepository;
    @Autowired
   FinalProductRepository FinalProductRepository;

    @GetMapping("/tartesSucre")
    public String tartesSucre(Model out) {
        out.addAttribute("halfFinshedFruit", halfFinshedRepository.findAllByType("fruit"));
        out.addAttribute("halfFinshedCreme", halfFinshedRepository.findAllByType("creme"));
        out.addAttribute("halfFinshed", halfFinshedRepository.findAll());
        out.addAttribute("newfinalProduct", new FinalProduct());
        return "tartesSucre";
    }

    @PostMapping("/finalProductSaveClient")
    public String saveFinalproduct(@ModelAttribute FinalProduct finalProduct) {
        try{
            FinalProductRepository.save(finalProduct);
        }catch (Exception ex){
        }

        return "redirect:tartesSucre";
    }
}
