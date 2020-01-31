package com.gourmandine.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "index")
public class FinalProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFp;
    private String refPf;
    private String name;
    private Double prices;
    private String description;
    private String img;
    private String status;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    private User user;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(name = "recette", joinColumns = @JoinColumn(name = "id_fp"), inverseJoinColumns = @JoinColumn(name = "id"))
    private List<HalfFinshed> halfFinsheds = new ArrayList<>();

    public String getStatus() {
        return status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<HalfFinshed> getHalfFinsheds() {
        return halfFinsheds;
    }

    public void setHalfFinsheds(List<HalfFinshed> halfFinsheds) {
        this.halfFinsheds = halfFinsheds;
    }

    public FinalProduct() {
    }

    public Long getIdFp() {
        return idFp;
    }

    public void setIdFp(Long id) {
        this.idFp = id;
    }

    public String getRefPf() {
        return refPf;
    }

    public void setRefPf(String refPf) {
        this.refPf = refPf;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
