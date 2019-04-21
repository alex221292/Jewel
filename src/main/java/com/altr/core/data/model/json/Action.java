package com.altr.core.data.model.json;

public class Action extends JsonBody {
    public String action;

    public String orderId;

    public Action(String action, String orderId) {
        this.action = action;
        this.orderId = orderId;
    }

    public Action() {
    }

    public String getAction() {
        return action;
    }

    public String getOrderId() {
        return orderId;
    }
}
