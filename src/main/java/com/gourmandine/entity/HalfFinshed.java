package com.gourmandine.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class HalfFinshed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refFp;
    private String refHf;
    private String name;
    private Double prices;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private User user;

    @OneToMany(mappedBy = "finalProduct", cascade = CascadeType.REFRESH)
    private List<Commande> commandes = new ArrayList<>();

    public List<Commande> getCommandes() {
        return commandes;
    }

    public void setCommandes(List<Commande> commandes) {
        this.commandes = commandes;
    }

    public HalfFinshed() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRefFp() {
        return refFp;
    }

    public void setRefFp(String refFp) {
        this.refFp = refFp;
    }

    public String getRefHf() {
        return refHf;
    }

    public void setRefHf(String refHf) {
        this.refHf = refHf;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
