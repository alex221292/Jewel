package com.altr.core.data.model;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by alexe on 07.10.2018.
 */
@Entity
@Table
public class TService {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String name;
    private String code;
    private float loss;

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public float getLoss() {
        return loss;
    }

    public void setLoss(float loss) {
        this.loss = loss;
    }

    @Override
    public String toString() {
        return "TService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", loss=" + loss +
                '}';
    }
}
