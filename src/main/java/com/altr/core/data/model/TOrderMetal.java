package com.altr.core.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

@Entity
@Table(name = "order_metals")
@AssociationOverrides({
        @AssociationOverride(name = "id.orderId", joinColumns = @JoinColumn(name = "order_id")),
        @AssociationOverride(name = "id.metalCatId", joinColumns = @JoinColumn(name = "metal_cat_id"))
})
public class TOrderMetal implements Serializable {

    @Id
    private TOrderMetalPK id;

    public TOrderMetalPK getId() {
        return id;
    }

    public void setId(TOrderMetalPK id) {
        this.id = id;
    }

    @Transient
    public BigInteger getOrderId() {
        return getId().getOrderId();
    }

    public void setOrderId(BigInteger orderId){
        getId().setOrderId(orderId);
    }

    @Transient
    public BigInteger getMetalCatId(){
        return getId().getMetalCatId();
    }

    public void setMetalCatId(BigInteger metalCatId){
        getId().setMetalCatId(metalCatId);
    }

    @Override
    public String toString() {
        return "TOrderMetal{" +
                "id=" + id +
                '}';
    }
}
