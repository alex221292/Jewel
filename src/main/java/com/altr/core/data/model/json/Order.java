package com.altr.core.data.model.json;

public class Order extends JsonBody {
    public String id = "";
    public String status = "";
    public String phone = "";
    public String name = "";
    public String cost = "";
    public String orderName = "";

    public Order() {
    }

    public Order(String id, String status, String phone, String name, String cost) {
        this.id = id;
        this.status = status;
        this.phone = phone;
        this.name = name;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getCost() {
        return cost;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
