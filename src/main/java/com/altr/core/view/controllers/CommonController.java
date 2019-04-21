package com.altr.core.view.controllers;

import com.altr.core.data.model.json.*;
import com.altr.core.data.service.CommonViewer;
import com.altr.core.tools.LoadingBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Controller
public class CommonController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonViewer commonViewer;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String defaultPage(@RequestParam Map<String, String> request,
                              Model model) throws Exception {
        model.addAttribute("title", "Test Page");
        return "index";
    }

    @MessageMapping("/orders")
    @SendTo("/topic/orders")
    public OrdersView sendOrders(Action action) throws Exception {
        OrdersView ordersView;
        if ("get_all".equals(action.action)) {
            ordersView = commonViewer.getAllOrdersJson();
        } else {
            ordersView = new OrdersView(Collections.<Order>emptyList());
        }
        return ordersView;
    }

    @MessageMapping("/order")
    @SendTo("/topic/order")
    public OrdersView sendOrderItem(Action action) throws Exception {
        OrdersView ordersView;
        ordersView = commonViewer.getOrderItemJson(action.orderId);
        return ordersView;
    }

}
