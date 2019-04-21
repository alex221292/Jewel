package com.altr.core.data.service.impl;

import com.altr.core.data.dao.OrderDao;
import com.altr.core.data.model.TOrder;
import com.altr.core.data.model.json.Order;
import com.altr.core.data.model.json.OrdersView;
import com.altr.core.data.service.CommonViewer;
import com.altr.core.data.service.factory.ViewFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service("commonViewerBean")
public class CommonViewerBean implements CommonViewer, InitializingBean {

    @Autowired
    private OrderDao orderDao;

    private static CommonViewerBean instance;

    @Override
    public void afterPropertiesSet() throws Exception {
        instance = this;
    }

    public static CommonViewerBean get() {
        return instance;
    }

    @Transactional
    public OrdersView getAllOrdersJson() {
        /*List<TOrder> orders = orderDao.findAllOrders();
        List<Order> ordersJson = new ArrayList<Order>();
        for (TOrder order : orders){
            ViewFactory viewFactory = new ViewFactory(new Order(), order);
            ordersJson.add((Order) viewFactory.run());
        }*/
        OrdersView ordersView = new OrdersView();
        ordersView = (OrdersView) new ViewFactory(ordersView).run();
        return ordersView;
    }

    @Transactional
    public OrdersView getOrderItemJson(String orderIdStr) {
        OrdersView ordersView = new OrdersView();
        try {
            double d = Double.parseDouble(orderIdStr);
        } catch (Exception e) {
            return ordersView;
        }
        ordersView = (OrdersView) new ViewFactory(ordersView, new BigInteger(orderIdStr)).run();
        return ordersView;
    }
}
