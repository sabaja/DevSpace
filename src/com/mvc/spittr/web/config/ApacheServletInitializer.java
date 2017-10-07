package com.mvc.spittr.web.config;

import java.lang.invoke.MethodHandles;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PreDestroy;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.annotation.WebInitParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;

import com.be.spittr.config.AppRootConfig;
import com.mvc.spittr.web.servlet.GenericServletDemoServlet;

public class ApacheServletInitializer implements WebApplicationInitializer, ServletContextListener{
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	/**
	 * Servlet Apache initializer
	 */
	@Override
	public void onStartup(ServletContext context) throws ServletException {
		logger.info("WebApplicationInitializer@ServletApacheInitializer instantiated");
		Dynamic servlet = context.addServlet("GenericServletDemoServlet", GenericServletDemoServlet.class);
		servlet.addMapping("/admin");
	    Map<String, String> initParams = new HashMap<>();
	    /*
	     * {
	            @WebInitParam(name="admin", value="Harry Taciak"),
	            @WebInitParam(name="email", value="admin@example.com")
	        }
	     */
	    
	    initParams.put("admin", "Jacopo Sabatini");
	    initParams.put("email", "jacoposabtini76@gmail.com");
		servlet.setInitParameters(initParams);
		servlet.setLoadOnStartup(2);
		logger.info("Servlet [{}] was added", GenericServletDemoServlet.class.getName());
	}

	//https://stackoverflow.com/questions/13532302/how-to-shutdown-connection-pool-on-context-unload
	//close jdbc driveres
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//desroy session
		new AppRootConfig().getSessionFactory().destroy();
		logger.info("Session destroyed!");
		Enumeration<Driver> drivers = DriverManager.getDrivers();
	    while (drivers.hasMoreElements()) {
	        Driver driver = drivers.nextElement();
	        try {
	            DriverManager.deregisterDriver(driver);
	            logger.info("deregistering jdbc driver: " + driver);
	        } catch (SQLException e) {
	            logger.error("error deregistering jdbc driver: " + driver, e);
	        }
	    }
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

		
}
