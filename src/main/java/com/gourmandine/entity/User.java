package com.gourmandine.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String email;
    private String name;
    private String firstname;
    private String adress;
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REFRESH)
    private List<FinalProduct> finalProducts = new ArrayList<>();

    @OneToMany(mappedBy = "client", cascade = CascadeType.REFRESH)
    private List<Commande> commandes = new ArrayList<>();

    public User() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<FinalProduct> getFinalProducts() {
        return finalProducts;
    }

    public void setFinalProducts(List<FinalProduct> finalProducts) {
        this.finalProducts = finalProducts;
    }

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }
}
