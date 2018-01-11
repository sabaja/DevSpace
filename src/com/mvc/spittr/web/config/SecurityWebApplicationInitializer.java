package com.mvc.spittr.web.config;

import java.lang.invoke.MethodHandles;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.multipart.support.MultipartFilter;
@Order(1)
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass()); 
	static{
		logger.info("1 ************************SecurityWebApplicationInitializer Intizialized************************");
	}

//	@Override
//    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
//        insertFilters(servletContext, new MultipartFilter());
//    }
}
