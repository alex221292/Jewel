package com.altr.core.system;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class CustomHibernateDaoSupport extends HibernateDaoSupport {

    @Autowired
    public void anyMethodName(SessionFactory sessionFactory)
    {
        setSessionFactory(sessionFactory);
    }

}
