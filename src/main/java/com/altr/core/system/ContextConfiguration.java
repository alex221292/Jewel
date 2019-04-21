package com.altr.core.system;

import com.altr.core.data.service.CommonViewer;
import com.altr.core.data.service.impl.CommonViewerBean;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.sql.DataSource;

/**
 * Created by alexe on 07.10.2018.
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("com.altr")
public class ContextConfiguration extends WebMvcConfigurerAdapter implements TransactionManagementConfigurer {

    @Bean
    public DataSource dataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        return dsLookup.getDataSource("jdbc/jewel");
    }

    @Bean
    public JdbcOperations jdbcOperations() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }

    @Bean
    public CommonViewer commonViewer(){
        return new CommonViewerBean();
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new LocalSessionFactoryBuilder(dataSource())
                .addAnnotatedClasses(com.altr.core.data.model.TClient.class,
                        com.altr.core.data.model.TService.class,
                        com.altr.core.data.model.TProduct.class,
                        com.altr.core.data.model.TStatus.class,
                        com.altr.core.data.model.TMeasure.class,
                        com.altr.core.data.model.TMetal.class,
                        com.altr.core.data.model.TMetCatalogObj.class,
                        com.altr.core.data.model.TStoneCatalog.class,
                        com.altr.core.data.model.TWork.class,
                        com.altr.core.data.model.TOrderMetal.class,
                        com.altr.core.data.model.TOrder.class)
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect").buildSessionFactory();
    }

}
