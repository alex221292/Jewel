package com.altr.core.tools;


import com.altr.core.system.ContextConfiguration;
import org.springframework.jdbc.core.JdbcOperations;

public class JdbcInstance {

    public static JdbcOperations getJDBC() throws IllegalAccessException, InstantiationException {
        return ContextConfiguration.class.newInstance().jdbcOperations();
    }

}
