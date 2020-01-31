package com.gourmandine.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "index")
public class HalfFinshed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double prices;
    private String img;
    private String type;

    @OneToMany(mappedBy = "finalProduct", cascade = CascadeType.REFRESH)
    private List<Commande> commandes = new ArrayList<>();

    @ManyToMany(mappedBy = "halfFinsheds", cascade = CascadeType.REFRESH)
    private List<FinalProduct> finalProducts = new ArrayList<>();

    public HalfFinshed() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FinalProduct> getFinalProducts() {
        return finalProducts;
    }

    public void setFinalProducts(List<FinalProduct> finalProducts) {
        this.finalProducts = finalProducts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrices() {
        return prices;
    }

    public void setPrices(Double prices) {
        this.prices = prices;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
