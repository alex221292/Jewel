package com.altr.core.data.service;

import com.altr.core.data.model.json.OrdersView;

public interface CommonViewer {
    public OrdersView getAllOrdersJson();
    public OrdersView getOrderItemJson(String orderId);
}
