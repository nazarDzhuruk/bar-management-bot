package com.drypalm.easybusiness.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SoftDrink {
    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private int productCode;
    private String name;
    private int quantityBottle;
    private float litre;

    @JsonIgnore
    @ManyToOne
    private Stock softStock;

    protected SoftDrink() {
    }

    public SoftDrink(int productCode, String name, int quantityBottle, float litre) {
        this.productCode = productCode;
        this.name = name;
        this.quantityBottle = quantityBottle;
        this.litre = litre;
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

    public int getQuantityBottle() {
        return quantityBottle;
    }

    public void setQuantityBottle(int quantityBottle) {
        this.quantityBottle = quantityBottle;
    }

    public float getLitre() {
        return litre;
    }

    public void setLitre(float litre) {
        this.litre = litre;
    }

    public Stock getSoftStock() {
        return softStock;
    }

    public void setSoftStock(Stock softStock) {
        this.softStock = softStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SoftDrink)) return false;
        SoftDrink softDrink = (SoftDrink) o;
        return productCode == softDrink.productCode && Objects.equals(id, softDrink.id) &&
                Objects.equals(name, softDrink.name) && Objects.equals(softStock, softDrink.softStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCode, name, softStock);
    }
}
