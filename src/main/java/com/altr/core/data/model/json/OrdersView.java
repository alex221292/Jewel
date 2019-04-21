package com.altr.core.data.model.json;

import java.util.ArrayList;
import java.util.List;

public class OrdersView extends JsonBody {

    private List<Order> orders = new ArrayList<Order>();

    public OrdersView() {
    }

    public OrdersView(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "OrdersView{" +
                "orders=" + orders +
                '}';
    }
}
