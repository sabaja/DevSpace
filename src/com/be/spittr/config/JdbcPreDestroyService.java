package com.be.spittr.config;

import java.lang.invoke.MethodHandles;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JdbcPreDestroyService{
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Autowired
	private AppRootConfig root;

	@PreDestroy
	public void destroy() throws Exception {
		SessionFactory factory = root.getSessionFactory().getObject();
		if (factory.isOpen()){
			factory.close();
		}

		logger.info("Session is closed!");
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

}
