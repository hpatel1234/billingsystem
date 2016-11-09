/**
 * WebAppInitializer.java
 * Jan 12, 2016
 */
package com.assignment.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.assignment.web.config.WebMVCConfig;

/**
 * <p>
 * Application initializer.
 * </p>
 * @author hemantp
 *
 */
public class WebAppInitializer implements WebApplicationInitializer {
    /**
     * @see org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet.ServletContext)
     */
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext webAppContext = new AnnotationConfigWebApplicationContext();
        webAppContext.register(WebMVCConfig.class);
        DispatcherServlet servlet = new DispatcherServlet(webAppContext);
        ServletRegistration.Dynamic appServlet = servletContext.addServlet("dispatcherServlet", servlet);
        appServlet.setLoadOnStartup(1);
        appServlet.setAsyncSupported(true);
        appServlet.addMapping("/","/assets/**");
        
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.setConfigLocation("com.assignment.config");
        servletContext.addListener(new ContextLoaderListener(rootContext));
    }
}
