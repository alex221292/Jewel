package com.altr.core.data.model;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by alexe on 07.10.2018.
 */
@Entity
@Table(name = "metals")
public class TMetal {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String name;

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TMetal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
