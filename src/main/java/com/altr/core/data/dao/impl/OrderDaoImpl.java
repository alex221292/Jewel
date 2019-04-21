package com.altr.core.data.dao.impl;

import com.altr.core.data.dao.OrderDao;
import com.altr.core.data.model.TOrder;
import com.altr.core.system.CustomHibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository("orderDao")
public class OrderDaoImpl extends CustomHibernateDaoSupport implements OrderDao {

    public TOrder findOrderById(BigInteger orderId) {
        List list = getHibernateTemplate().find("from TOrder where id = ?", orderId);
        if (list != null && list.size() > 0) {
            return (TOrder) list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<TOrder> findAllOrders() {
        return ((List<TOrder>) getHibernateTemplate().find("from TOrder order by id"));
    }

    public void delete(TOrder order) {
        if (order != null) {
            getHibernateTemplate().delete(order);
        }
    }

    @Override
    public void save(TOrder order) {
        if (order != null) {
            getHibernateTemplate().saveOrUpdate(order);
        }
    }
}
