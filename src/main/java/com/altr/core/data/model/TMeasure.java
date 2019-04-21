package com.altr.core.data.model;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by alexe on 07.10.2018.
 */
@Entity
@Table(name = "measure")
public class TMeasure {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String value;

    public BigInteger getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TMeasure{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
