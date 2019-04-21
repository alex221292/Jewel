package com.altr.core.data.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "works")
public class TWork {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private Integer art;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private TProduct product;

    private String name;

    private float cost;

    public BigInteger getId() {
        return id;
    }

    public Integer getArt() {
        return art;
    }

    public void setArt(Integer art) {
        this.art = art;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "TWork{" +
                "id=" + id +
                ", art=" + art +
                ", product=" + product +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
