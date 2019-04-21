package com.altr.core.data.service.factory;


import com.altr.core.data.model.TClient;
import com.altr.core.data.model.TOrder;
import com.altr.core.data.model.json.JsonBody;
import com.altr.core.data.model.json.Order;
import com.altr.core.data.model.json.OrdersView;
import com.altr.core.tools.CommonTools;
import com.altr.core.tools.JdbcInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class ViewFactory extends AbstractFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewFactory.class);

    private static final String QUERY_GET_COST_FOR_ORDER = "select sum(cost) from (select om.count * m.cost as cost\n" +
            "from order_metals om,\n" +
            "met_catalog m\n" +
            "where om.order_id = ?\n" +
            "and m.id = om.metal_cat_id\n" +
            "union all\n" +
            "select os.count * s.cost as cost\n" +
            "from order_stones os,\n" +
            "stones_catalog s\n" +
            "where os.order_id = ?\n" +
            "and s.id = os.stone_cat_id\n" +
            "union all\n" +
            "select w.cost as cost\n" +
            "from order_works ow,\n" +
            "works w\n" +
            "where ow.order_id = ?\n" +
            "and w.id = ow.work_id) t";

    private static final String QUERY_GET_ALL_ORDERS = "select o.id, s.value as status, o.name as order_name, c.phone, concat(c.name, ' ', c.name2, ' ', c.soname) cust_name\n" +
            "from orders o,\n" +
            "clients c,\n" +
            "statuses s\n" +
            "where c.id = o.client_id\n" +
            "and s.id = o.status_id";

    private static final String QUERY_GET_ORDER_ITEM = "select o.id, s.value as status, o.name as order_name, c.phone, concat(c.name, ' ', c.name2, ' ', c.soname) cust_name\n" +
            "from orders o,\n" +
            "clients c,\n" +
            "statuses s\n" +
            "where o.id = ?\n" +
            "and c.id = o.client_id\n" +
            "and s.id = o.status_id";

    private Object object;
    private Object data;
    private BigInteger orderId;

    public ViewFactory(Object object, Object data) {
        this.object = object;
        this.data = data;
    }

    public ViewFactory(Object object) {
        this.object = object;
    }

    public ViewFactory(Object object, BigInteger orderId) {
        this.object = object;
        this.orderId = orderId;
    }

    public JsonBody run() {
        if (object instanceof Order
                && data != null
                && data instanceof TOrder
                && orderId == null) {
            Order order = (Order) object;
            TOrder tOrder = (TOrder) data;
            order.setId(tOrder.getId().toString());
            order.setStatus(tOrder.getStatus().getValue());
            order.setOrderName(tOrder.getName());
            TClient client = tOrder.getClient();
            if (client != null) {
                order.setPhone(client.getPhone());
            }
            order.setName(getConcatenatedName(tOrder));
            calculateOrderCost(order, tOrder.getId());
            return order;
        } else if (object instanceof OrdersView
                && orderId == null) {
            OrdersView ordersView = (OrdersView) object;
            try {
                List<Map<String, Object>> rows = JdbcInstance.getJDBC().queryForList(QUERY_GET_ALL_ORDERS);
                for (Map row : rows) {
                    Order order = new Order();
                    order.setId(row.get("id").toString());
                    order.setStatus((String) row.get("status").toString());
                    order.setOrderName((String) row.get("order_name").toString());
                    order.setPhone((String) row.get("phone").toString());
                    order.setName((String) row.get("cust_name").toString());
                    calculateOrderCost(order, new BigInteger(order.getId()));
                    ordersView.getOrders().add(order);
                }
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
            return ordersView;
        } else if (object instanceof OrdersView
                && orderId != null) {
            OrdersView ordersView = (OrdersView) object;
            try {
                List<Map<String, Object>> rows = JdbcInstance.getJDBC().queryForList(QUERY_GET_ORDER_ITEM, orderId);
                for (Map row : rows) {
                    Order order = new Order();
                    order.setId(row.get("id").toString());
                    order.setStatus((String) row.get("status").toString());
                    order.setOrderName((String) row.get("order_name").toString());
                    order.setPhone((String) row.get("phone").toString());
                    order.setName((String) row.get("cust_name").toString());
                    calculateOrderCost(order, new BigInteger(order.getId()));
                    ordersView.getOrders().add(order);
                }
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
            return ordersView;
        }
        return null;
    }

    private String getConcatenatedName(TOrder order) {
        if (order != null) {
            TClient client = order.getClient();
            if (client != null) {
                return CommonTools.getSafeString(client.getName()) +
                        " " +
                        CommonTools.getSafeString(client.getName2()) +
                        " " +
                        CommonTools.getSafeString(client.getSoname());
            }
        }
        return "";
    }

    private void calculateOrderCost(Order order, BigInteger orderId){
        try {
            Float cost = JdbcInstance.getJDBC().queryForObject(QUERY_GET_COST_FOR_ORDER
                    , new Object[]{orderId, orderId, orderId}
                    , Float.class);
            order.setCost(cost.toString());
        } catch (Exception e){
            LOGGER.info(e.getMessage());
            order.setCost("UNABLE TO CALCULATE");
        }
    }

}
