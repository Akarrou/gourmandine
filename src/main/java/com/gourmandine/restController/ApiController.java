package com.gourmandine.restController;

import com.gourmandine.entity.FinalProduct;
import com.gourmandine.repository.FinalProductRepository;
import com.gourmandine.repository.HalfFinshedRepository;
import com.gourmandine.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {
    @Autowired
    FinalProductRepository finalProductRepository;

    @Autowired
    HalfFinshedRepository halfFinshedRepository;

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = "/api/finalproduct", produces = "application/json")
    public List<FinalProduct> getAllFinalProduct() {
        return finalProductRepository.findAll();
    }

    @GetMapping(path = "/api/finalproduct/{id}", produces = "application/json")
    public FinalProduct getByFinalProduct(@PathVariable Long id) {
        return finalProductRepository.findById(id).get();
    }

    @PostMapping(path = "/api/finalproduct", produces = "application/json")
    public List<FinalProduct> newFinalProduct(@RequestBody FinalProduct finalProduct) {
        finalProductRepository.save(finalProduct);
        return finalProductRepository.findAll();
    }

    @PutMapping(path = "/api/finalproduct", produces = "application/json")
    public List<FinalProduct> udapteFinalProduct(@RequestBody FinalProduct finalProduct) {
        finalProductRepository.save(finalProduct);
        return finalProductRepository.findAll();
    }

    @DeleteMapping(path = "/api/finalproduct/{id}", produces = "application/json")
    public List<FinalProduct> deleteFinalProduct(@PathVariable Long id) {
        finalProductRepository.deleteById(id);
        return finalProductRepository.findAll();
    }
}
