package com.drypalm.easybusiness.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Food {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int productCode;
    private String name;
    private int quantity;

    @JsonIgnore
    @ManyToOne
    private Stock foodStock;

    protected Food() {
    }

    public Food(int productCode, Long id, String name, int quantity) {
        this.productCode = productCode;
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getProductCode() {
        return productCode;
    }

    public void setProductCode(int productCode) {
        this.productCode = productCode;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Stock getFoodStock() {
        return foodStock;
    }

    public void setFoodStock(Stock foodStock) {
        this.foodStock = foodStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Food)) return false;
        Food food = (Food) o;
        return productCode == food.productCode && Objects.equals(id, food.id) &&
                Objects.equals(name, food.name) && Objects.equals(foodStock, food.foodStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCode, name, foodStock);
    }
}
