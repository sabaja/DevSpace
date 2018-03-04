package com.mvc.spittr.bootstrap.main;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Enumeration;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import net.bytebuddy.implementation.bind.annotation.This;

//@WebServlet(name = "Init", urlPatterns = { "/init" })
public class SpringWebAppInitializer implements Servlet, WebApplicationInitializer, ServletConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3906574057759928087L;
	private transient ServletConfig config;
//	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
//	static {
//		logger.info("************************" + This.class.getSimpleName() + "************************");
//	}

	@Override
	public void onStartup(ServletContext container) throws ServletException {

		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.setConfigLocations("com.mvc.spittr.web.config");

		ServletRegistration.Dynamic dispatcher = container.addServlet("SpringDispatcher",
				new DispatcherServlet(appContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");
	}

	public static void main(String[] args) {
		try {
			new SpringWebAppInitializer().onStartup(new SpringWebAppInitializer().getServletContext());
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getServletName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServletContext getServletContext() {

		return this.getServletConfig().getServletContext();
	}

	@Override
	public String getInitParameter(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enumeration<String> getInitParameterNames() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;

	}

	@Override
	public ServletConfig getServletConfig() {

		return config;
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}