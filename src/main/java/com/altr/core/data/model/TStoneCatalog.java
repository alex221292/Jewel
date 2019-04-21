package com.altr.core.data.model;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "stones_catalog")
public class TStoneCatalog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private Integer art;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mes_id")
    private TMeasure measure;

    private String name;
    private String comment;
    private float cf;

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

    public TMeasure getMeasure() {
        return measure;
    }

    public void setMeasure(TMeasure measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public float getCf() {
        return cf;
    }

    public void setCf(float cf) {
        this.cf = cf;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "TStoneCatalog{" +
                "id=" + id +
                ", art=" + art +
                ", measure=" + measure +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", cf=" + cf + '\'' +
                ", cost=" + cost +
                '}';
    }

}
