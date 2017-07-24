package a1com.mvc.spittr.web.config;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

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

//The only gotcha with configuring DispatcherServlet in this way, as opposed to in
//a web.xml file, is that it will only work when deploying to a server that supports
//Servlet 3.0, such as Apache Tomcat 7 or higher. The Servlet 3.0 specification has been
//final since December 2009, and the odds are good that you’ll be deploying your applications
//to a servlet container that supports Servlet 3.0.
//You’ll have no choice but to configure Dispatcher-
// Servlet in web.xml. We’ll look at web.xml and other configuration options in
// chapter 7.



public class ServletDispatcherConfigInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
//	Under the covers, AbstractAnnotationConfigDispatcherServletInitializer creates
//	both a DispatcherServlet and a ContextLoaderListener.
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Override
	protected Class<?>[] getRootConfigClasses() {
		logger.info("getRootConfigClasses()::AppRootConfig.class");
		return new Class<?>[] {AppRootConfig.class}; // RootConfig.class root configuration is defined in RootConfig
		// the @Configuration class’s returned getRootConfigClasses() will be
		// used to configure the application context created by
		// ContextLoaderListener.
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		logger.info("getServletConfigClasses()::AppWebConfig.class");
		return new Class<?>[] {AppWebConfig.class };// DispatcherServlet’s configuration is declared in WebConfig
		// The @Configuration
		// classes returned from getServletConfigClasses() will define beans for
		// Dispatcher-
		// Servlet’s application context.
		// These beans are typically the middle-tier and data-tier components
		// that drive the back end of the application.

	}

	@Override
	protected String[] getServletMappings() {

		return new String[] { "/" };// In this case, it’s mapped to /,
									// indicating that it will be the
									// application’s default servlet. It will
									// handle all requests coming into the
									// application.
	}
}
