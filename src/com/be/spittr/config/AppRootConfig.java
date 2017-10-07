package com.be.spittr.config;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

import javax.annotation.PreDestroy;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
import org.springframework.webflow.config.AbstractFlowConfiguration;

import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.entity.Spittle;

/**
 * 
 * @author sabaja The only significant thing to note in AppRootConfig is that
 *         it’s annotated with @Component- Scan . There will be plenty of
 *         opportunities throughout this book to flesh out Root- Config with
 *         non-web components. You’re almost ready to start building a web
 *         application with Spring MVC . The big question at this point is what
 *         application you’ll build.
 */

@Configuration
@PropertySource("classpath:hibernate-mysql.properties")
@ComponentScans(
		value = {
		@ComponentScan(
				basePackages = { "com.mvc.spittr.dao", "com.mvc.spittr.service", "com.mvc.spittr.service.backup" }, 
				excludeFilters = { @Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)}
				)
			}
		)
@EnableTransactionManagement
public class AppRootConfig{

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Autowired
	private Environment env;

	/**
	 * To use tag message <s:message code="spittr.home" /> with
	 * messages.properties
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		logger.info("MessageSource config");
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
	 * registration) method. see
	 * {@com.mvc.spittr.web.config.ConfigInitializerFORServletDispatcherMultipartConfig}
	 */

	@Bean
	public MultipartResolver multipartResolver() throws IOException {
		logger.info("MultipartResolver config");
		return new StandardServletMultipartResolver();
	}

	/**
	 * CONFIGURING A JAKARTA COMMONS FILE UPLOAD MULTIPART RESOLVER
	 * 
	 * StandardServletMultipartResolver is usually the best choice, but if
	 * you’re not deploying your application to a Servlet 3.0 container, you’ll
	 * need an alternative. You can write your own implementation of the
	 * MultipartResolver interface if you’d like. But unless you need to perform
	 * some special handling during multipart request han- dling, there’s no
	 * reason to do that. Spring offers CommonsMultipartResolver as an
	 * out-of-the-box alternative to StandardServletMultipartResolver
	 * 
	 * Unlike StandardServletMultipartResolver , there’s no need to configure a
	 * tempo- rary file location with CommonsMultipartResolver . By default, the
	 * location is the serv- let container’s temporary directory. But you can
	 * specify a different location by setting the uploadTempDir property:
	 */

	// @Bean
	// public MultipartResolver multipartResolver() throws IOException {
	// CommonsMultipartResolver multipartResolver = new
	// CommonsMultipartResolver();
	// multipartResolver.setUploadTempDir(new
	// FileSystemResource("/tmp/spittr/uploads"));
	// multipartResolver.setMaxUploadSize(2097152);
	// multipartResolver.setMaxInMemorySize(0);
	// return multipartResolver;
	// }
	/**
	 * Unlike MultipartConfigElement , however, there’s no way to specify the
	 * maximum multipart request size.
	 */

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.connection.driver_class"));
		dataSource.setUrl(env.getProperty("db.connection.url"));
		dataSource.setUsername(env.getProperty("db.connection.username"));
		dataSource.setPassword(env.getProperty("db.connection.password"));
		logger.info("DB DataSource config - HIBERNATE - ");
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(getDataSource());
		logger.info("LocalSessionFactoryBean config - HIBERNATE -");

		factoryBean.setHibernateProperties(getHibernateProperties());
		logger.info("HibernateProperties config - HIBERNATE -");

		factoryBean.setAnnotatedClasses(Spitter.class, Spittle.class);

		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		logger.info("HibernateTransactionManager config - HIBERNATE -");

		return transactionManager;
	}

	private Properties getHibernateProperties() {
		Properties props = new Properties();
		props.put(AvailableSettings.DIALECT, env.getProperty("hb.dialect"));
		props.put(AvailableSettings.SHOW_SQL, env.getProperty("hb.show_sql"));
		props.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("hb.hbm2ddl.auto"));
		props.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, env.getProperty("hb.current.session.context.class"));
		return props;
	}

}