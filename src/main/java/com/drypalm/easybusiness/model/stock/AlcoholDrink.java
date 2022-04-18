package com.drypalm.easybusiness.model.stock;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AlcoholDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int productCode;
    private String name;
    private int quantityBottle;
    private float litre;
    private String type;

    @JsonIgnore
    @ManyToOne
    private Stock alcoholStock;

    protected AlcoholDrink() {
    }

    public AlcoholDrink(int productCode, String name, String type, int quantityBottle, float litre) {
        this.productCode = productCode;
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Stock getAlcoholStock() {
        return alcoholStock;
    }

    public void setAlcoholStock(Stock alcoholStock) {
        this.alcoholStock = alcoholStock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlcoholDrink)) return false;
        AlcoholDrink that = (AlcoholDrink) o;
        return productCode == that.productCode && Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) && Objects.equals(type, that.type) &&
                Objects.equals(alcoholStock, that.alcoholStock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productCode, name, type, alcoholStock);
    }
}
