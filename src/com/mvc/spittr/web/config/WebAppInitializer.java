package com.mvc.spittr.web.config;

import java.io.File;
import java.lang.invoke.MethodHandles;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mvc.spittr.constants.SpitterConsts;
import com.mvc.spittr.web.logFiltersInterceptors.LoggingUrlRequestFilter;

/**
 * DispatcherServlet is the centerpiece of Spring MVC. It’s where the request
 * first hits the framework, and it’s responsible for routing the request
 * through all the other components. Historically, servlets like
 * DispatcherServlet have been configured in a web.xml file that’s carried in
 * the web application’s WAR file. Certainly that’s one option for configuring
 * DispatcherServlet. But thanks to recent advances in the Servlet 3
 * specification and in Spring 3.1, it’s not the only option. And it’s not the
 * option we’ll go with in this chapter. Instead of a web.xml file, you’re going
 * to use Java to configure DispatcherServlet in the servlet container. The
 * following listing shows the Java class you’ll need
 */

// The only gotcha with configuring DispatcherServlet in this way, as opposed to
// in
// a web.xml file, is that it will only work when deploying to a server that
// supports
// Servlet 3.0, such as Apache Tomcat 7 or higher. The Servlet 3.0 specification
// has been
// final since December 2009, and the odds are good that you’ll be deploying
// your applications
// to a servlet container that supports Servlet 3.0.
// You’ll have no choice but to configure Dispatcher-
// Servlet in web.xml. We’ll look at web.xml and other configuration options in
// chapter 7.
@Order(2)
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	// Under the covers, AbstractAnnotationConfigDispatcherServletInitializer
	// creates
	// both a DispatcherServlet and a ContextLoaderListener.

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	static{
		logger.info("************************Start WebAppInitializer************************");
	}

	// private final String DOWNLOAD_PATH =
	// "/home/sabaja/Scrivania/spring-hibernate-workspace/Spring-In-Action_WEBApp/Downloads";
	// private final Long maxFileSize = 2_097_152L;
	// private final Long maxRequestSize = 4_194_304L;
	// private final int fileSizeThreshold = 0;

	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("AppRootConfig configured");
		//return new Class<?>[] { AppRootConfig.class, WebSecurityConfig.class };
		return new Class<?>[] { AppRootConfig.class, WebSecurityConfig.class}; // RootConfig.class root
														// configuration is
														// defined in RootConfig
		// the @Configuration class’s returned getRootConfigClasses() will be
		// used to configure the application context created by
		// ContextLoaderListener.
		// ContextLoaderListener is expected to load beans are typically the
		// middle-tier and data-tier components that drive the back end of the
		// application.
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		logger.info("AppWebConfig configured", this.DEFAULT_SERVLET_NAME);
		return new Class<?>[] { AppWebConfig.class };// DispatcherServlet’s
														// configuration is
														// declared in WebConfig
		// The @Configuration
		// classes returned from getServletConfigClasses() will define beans for
		// Dispatcher-
		// Servlet’s application context.
		// DispatcherServlet is expected to load beans containing web components
		// such as controllers, view resolvers, and handler mappings.

	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };// In this case, it’s mapped to /,
									// indicating that it will be the
									// application’s default servlet. It will
									// handle all requests coming into the
									// application.
	}

	// With the ServletRegistration.Dynamic that’s given to
	// customizeRegistration() ,
	// you can do several things, including set the load-on-startup priority by
	// calling set
	// LoadOnStartup() , set an initialization parameter by calling
	// setInitParameter() , and
	// call setMultipartConfig() to configure Servlet 3.0 multipart support. In
	// the preceding
	// example, you’re setting up multipart support to temporarily store
	// uploaded files
	// at /tmp/spittr/uploads.
	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("Author", "Jacopo Sabatini");
		logger.info("CustomizeRegistration initialized");

		// MultipartConfiguration for uploading file see at
		// com.mvc.spittr.web.config.AppRootConfig for @Bean
		// compatible to The Servlet 3.0 StandardServletMultipartResolver
		// MultipartConfigElement(java.lang.String location, long maxFileSize,
		// long maxRequestSize, int fileSizeThreshold)
		// https://docs.oracle.com/javaee/7/api/javax/servlet/MultipartConfigElement.html
		// Senza istanziare un oggetto File ottengo java.nio.file.NoSuchFileException nel momento dell'upload		
		File directory = new File(SpitterConsts.TEMPDIR.getStr());
		logger.info("***START*** Multipart initializing ");
		logger.info("Download Directory:{}", SpitterConsts.TEMPDIR.getStr());
		MultipartConfigElement element = new MultipartConfigElement(directory.getAbsolutePath(), SpitterConsts.MAX_FILE_SIZE.getLnum(),
				SpitterConsts.MAX_REQUEST_SIZE.getLnum(), SpitterConsts.FILE_SIZE_THRESHOLD.getNum()); 
		
		//https://www.mkyong.com/spring-mvc/spring-mvc-file-upload-example/
		registration.setMultipartConfig(element);
				
		registration.setLoadOnStartup(1);
		logger.info("Multipart uploads files at {}, maxFileSize={}, maxRequestSize={}, fileSizeThreshold={}",
				SpitterConsts.DOWNLOAD_PATH.getStr(), SpitterConsts.MAX_FILE_SIZE.getLnum(),
				SpitterConsts.MAX_REQUEST_SIZE.getLnum(), SpitterConsts.FILE_SIZE_THRESHOLD.getNum());
		logger.info("***END*** Multipart initialized ");
	}

	/**
	 * Filter initializer As you can see, this method returns an array of
	 * javax.servlet.Filter . Here it only returns a single filter, but it could
	 * return as many filters as you need. There’s no need to declare the
	 * mapping for the filters; any filter returned from getServletFilters()
	 * will automatically be mapped to DispatcherServlet .
	 * 
	 * 
	 * @Override public void onStartup(ServletContext servletContext) throws
	 *           ServletException{ servletContext.addFilter("logginFilter",
	 *           LoggingUrlRequestFilter.class).addMappingForUrlPatterns(null,
	 *           true, "/*"); }
	 * @Override protected Filter[] getServletFilters() { return new Filter[] {
	 *           new LoggingUrlRequestFilter() };
	 * 
	 *           }
	 */
	static{
		logger.info("************************End WebAppInitializer************************");
	}

}
