package com.altr.core.data.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigInteger;

@Embeddable
public class TOrderMetalPK implements Serializable {

    private static final long serialVersionUID = 3543236089104499993L;

    @Column(name = "order_id")
    private BigInteger orderId;

    @Column(name = "metal_cat_id")
    private BigInteger metalCatId;

    public TOrderMetalPK() {
    }

    public TOrderMetalPK(BigInteger orderId, BigInteger metalCatId) {
        this.orderId = orderId;
        this.metalCatId = metalCatId;
    }

    public BigInteger getOrderId() {
        return orderId;
    }

    public void setOrderId(BigInteger orderId) {
        this.orderId = orderId;
    }

    public BigInteger getMetalCatId() {
        return metalCatId;
    }

    public void setMetalCatId(BigInteger metalCatId) {
        this.metalCatId = metalCatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TOrderMetalPK that = (TOrderMetalPK) o;

        if (orderId != null ? !orderId.equals(that.orderId) : that.orderId != null) return false;
        return metalCatId != null ? metalCatId.equals(that.metalCatId) : that.metalCatId == null;
    }

    @Override
    public int hashCode() {
        int result = orderId != null ? orderId.hashCode() : 0;
        result = 31 * result + (metalCatId != null ? metalCatId.hashCode() : 0);
        return result;
    }
}
