package com.mvc.spittr.config;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

/**
 * 
 * @author sabaja The only significant thing to note in AppRootConfig is that
 *         it’s annotated with @Component- Scan . There will be plenty of
 *         opportunities throughout this book to flesh out Root- Config with
 *         non-web components. You’re almost ready to start building a web
 *         application with Spring MVC . The big question at this point is what
 *         application you’ll build.
 * 
 *         "com.mvc.spittr.web.config"
 */

@Configuration
@PropertySources(@PropertySource("classpath:mail-smtp.properties"))
public class AppRootConfig {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	static {
		logger.info("************************AppRootConfig************************");
	}
	@Autowired
	private Environment env;

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		logger.info("MessageSource initialized");
		return messageSource;
	}

	/**
	 * CONFIGURING A STANDARD SERVLET MULTIPART RESOLVER Standard Multipart
	 * 
	 * Resolver UPLOAD coming with The Servlet 3.0-compatible
	 * StandardServletMultipartResolver has no constructor arguments or
	 * properties to be set. This makes it extremely simple to declare as a bean
	 * in your Spring configuration, if you need further configurations you must
	 * adding them in customizeRegistration(ServletRegistration.Dynamic
	 * registration) method. see {@com.mvc.spittr.web.config.WebAppInitializer}
	 */
	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		logger.info("MultipartResolver initialized");
		return new StandardServletMultipartResolver();
	}


	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSender mailSender = new JavaMailSenderImpl();
		((JavaMailSenderImpl) mailSender).setHost(env.getProperty("spring.mail.host"));
		((JavaMailSenderImpl) mailSender).setPort(new Integer(env.getProperty("spring.mail.port")));
		((JavaMailSenderImpl) mailSender).setUsername(env.getProperty("spring.mail.username"));
		((JavaMailSenderImpl) mailSender).setPassword(env.getProperty("spring.mail.password"));
		logger.info("JavaMailMessage initialized");
		return mailSender;
	}
}