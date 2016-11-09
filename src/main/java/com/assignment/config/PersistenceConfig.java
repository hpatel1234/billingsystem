/**
 * PersistenceConfig.java
 * Feb 15, 2016
 */
package com.assignment.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * <p>
 * Configuration class, to accommodate DB configuration.
 * </p>
 * 
 * @author hemantp
 * 
 */
@Configuration
@ImportResource(value = "classpath:conf/data-source.xml")
public class PersistenceConfig {
   
	@Autowired
	private DataSource dataSource;
    
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        final LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("com.assignment.domainObjects");
        final Properties hibernateProperties = new Properties();
        hibernateProperties.put("dialect", "org.hibernate.dialect.HSQLDialect");
        hibernateProperties.put("show_sql", true);
       //hibernateProperties.put("hbm2ddl.auto", "create");
        sessionFactory.setHibernateProperties(hibernateProperties);
        return sessionFactory;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager() {
        final HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }
}
