package com.altr.core.data.dao;

import com.altr.core.data.model.TOrder;

import java.math.BigInteger;
import java.util.List;

public interface OrderDao {
    public TOrder findOrderById(BigInteger orderId);
    public List<TOrder> findAllOrders();
    public void delete(TOrder order);
    public void save(TOrder order);
}
