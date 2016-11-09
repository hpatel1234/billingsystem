/**
 * RootConfig.java
 * Feb 15, 2016
 */
package com.assignment.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * <p>
 * Configuration class, to define all the configuration needed across application.
 * </p>
 * 
 * @author hemantp
 * 
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.assignment.service", "com.assignment.business.service",
        "com.assignment.dao" })
public class RootConfig {
    
}
