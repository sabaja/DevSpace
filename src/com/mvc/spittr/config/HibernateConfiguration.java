package com.mvc.spittr.config;

import java.lang.invoke.MethodHandles;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@PropertySource("classpath:hibernate-mysql.properties")
@EnableTransactionManagement
@Configuration
@ComponentScans(value = { @ComponentScan(basePackages = { "com.mvc.spittr.dao", "com.mvc.spittr.service",
		"com.mvc.spittr.service.backup" }, excludeFilters = {
				@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) }) })

public class HibernateConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	static {
		logger.info("************************HibernateConfiguration************************");
	}
	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(env.getProperty("db.connection.driver_class"));
		dataSource.setUrl(env.getProperty("db.connection.url"));
		dataSource.setUsername(env.getProperty("db.connection.username"));
		dataSource.setPassword(env.getProperty("db.connection.password"));
		logger.info("***HIBERNATE*** - DB DataSource initialized - HIBERNATE -");
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean getSessionFactory() {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		logger.info("***HIBERNATE*** - LocalSessionFactoryBean initialized - HIBERNATE -");
		factoryBean.setHibernateProperties(getHibernateProperties());
		logger.info("***HIBERNATE*** - HibernateProperties initialized - HIBERNATE -");

		factoryBean.setPackagesToScan("com.mvc.spittr.entity");

		return factoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());
		logger.info("***HIBERNATE*** - HibernateTransactionManager initialized - HIBERNATE -");

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
