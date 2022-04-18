package com.drypalm.easybusiness.model.stock;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "foodStock")
    private Set<Food> foodSet = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "softStock")
    private Set<SoftDrink> softDrinkSet = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "alcoholStock")
    private Set<AlcoholDrink> alcoholDrinkSet = new HashSet<>();

    public Stock() {
    }

    public Stock(Set<Food> foodSet, Set<SoftDrink> softDrinkSet,
                 Set<AlcoholDrink> alcoholDrinkSet) {
        this.foodSet = foodSet;
        this.softDrinkSet = softDrinkSet;
        this.alcoholDrinkSet = alcoholDrinkSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Food> getFoodSet() {
        return foodSet;
    }

    public void setFoodSet(Set<Food> foodSet) {
        this.foodSet = foodSet;
    }

    public Set<SoftDrink> getSoftDrinkSet() {
        return softDrinkSet;
    }

    public void setSoftDrinkSet(Set<SoftDrink> softDrinkSet) {
        this.softDrinkSet = softDrinkSet;
    }

    public Set<AlcoholDrink> getAlcoholDrinkSet() {
        return alcoholDrinkSet;
    }

    public void setAlcoholDrinkSet(Set<AlcoholDrink> alcoholDrinkSet) {
        this.alcoholDrinkSet = alcoholDrinkSet;
    }

}
