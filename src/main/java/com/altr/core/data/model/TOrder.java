package com.altr.core.data.model;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * Created by alexe on 07.10.2018.
 */
@Entity
@Table(name = "orders")
public class TOrder {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private TClient client;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private TStatus status;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private TProduct product;

    private String name;

    @ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private TService service;

    public BigInteger getId() {
        return id;
    }

    public TClient getClient() {
        return client;
    }

    public void setClient(TClient client) {
        this.client = client;
    }

    public TStatus getStatus() {
        return status;
    }

    public void setStatus(TStatus status) {
        this.status = status;
    }

    public TProduct getProduct() {
        return product;
    }

    public void setProduct(TProduct product) {
        this.product = product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TService getService() {
        return service;
    }

    public void setService(TService service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "TOrder{" +
                "id=" + id +
                ", client=" + client +
                ", status=" + status +
                ", product=" + product +
                ", name='" + name + '\'' +
                ", service=" + service +
                '}';
    }
}
