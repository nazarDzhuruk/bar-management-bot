package com.drypalm.easybusiness.model.order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SoldProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int quantity;
    private float litre;

    protected SoldProduct() {
    }

    public SoldProduct(String name, int quantity, float litre) {
        this.name = name;
        this.quantity = quantity;
        this.litre = litre;
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

    public float getLitre() {
        return litre;
    }

    public void setLitre(float litre) {
        this.litre = litre;
    }

    public static SoldProduct.SoldProductBuilder builder() {
        return new SoldProductBuilder();
    }

    public static class SoldProductBuilder {
        private String name;
        private int quantity;
        private float litre;

        public SoldProduct.SoldProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SoldProduct.SoldProductBuilder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public SoldProduct.SoldProductBuilder litre(float litre) {
            this.litre = litre;
            return this;
        }

        public SoldProduct build() {
            return new SoldProduct(this.name, this.quantity, this.litre);
        }
    }
}
