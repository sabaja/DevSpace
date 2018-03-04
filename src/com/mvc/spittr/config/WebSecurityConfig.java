package com.mvc.spittr.config;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;

import com.mvc.spittr.dao.HibernateTokenRepositoryImpl;
import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.service.SpitterDetailService;

/**
 * 
 * example by:
 * 
 * http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
 * 
 * By SpingInAction AbstractSecurityWebApplicationInitializer , it will
 * intercept requests coming into the application and delegate them to a bean
 * whose ID is springSecurityFilter-Chain, which is another special filter known
 * as FilterChainProxy . It’s a single filter that chains together one or more
 * addi- tional filters. Spring Security relies on several servlet filters to
 * provide different secu- rity features, but you should almost never need to
 * know these details, as you likely won’t need to explicitly declare the
 * springSecurityFilterChain bean or any of the filters it chains together.
 * Those filters will be created when you enable web security.
 * 
 * @author sabaja
 *
 */
/*
 * public class SecurityWebInitializer extends
 * AbstractSecurityWebApplicationInitializer{
 * 
 * }
 */

/**
 * https://stackoverflow.com/questions/841231/fixing-beannotofrequiredtypeexception-on-spring-proxy-cast-on-a-non-singleton-be
 * https://spring.io/guides/gs/securing-web/
 * http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableWebSecurity
@ComponentScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	static {
		logger.info("************************Start - WebSecurityConfig************************");
	}
	// @Bean
	// public AuthenticationTrustResolver authenticationTrustResolver(){
	// return new AuthenticationTrustResolverImpl();
	// }

	// Evaluates Authentication tokens
	// @Bean
	// public AuthenticationTrustResolver authenticationTrustResolver(){
	// return new AuthenticationTrustResolverImpl();
	// }

	// @Autowired
	//// @Qualifier("spitterDetailService")
	// private UserDetailsService userDetailService;

	/**
	 * RememberMe functionality using custom PersistentTokenRepository
	 * implementation with Hibernate HibernateTokenRepositoryImpl
	 */
	@Autowired
	private HibernateTokenRepositoryImpl tokenRepository;

	@Bean
	public SpitterDetailService getSpitterDetailService() {
		logger.info("SpitterDetailService  initialized");
		return new SpitterDetailService();
	}

	@Bean
	public HibernateTokenRepositoryImpl getTokenRepository() {
		logger.info("HibernateTokenRepositoryImpl  initialized");
		return new HibernateTokenRepositoryImpl();
	}

	// Evaluates Authentication tokens
	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		logger.info("AuthenticationTrustResolver  initialized");
		return new AuthenticationTrustResolverImpl();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * http.requiresChannel().antMatchers("/login").requiresSecure().and().
		 * formLogin().loginPage("/login")
		 * .loginProcessingUrl("/login").usernameParameter("username").
		 * passwordParameter("password").and()
		 * .rememberMe().rememberMeParameter("remember-me").tokenRepository(
		 * tokenRepository)
		 * .tokenValiditySeconds(60).and().csrf().and().exceptionHandling().
		 * accessDeniedPage("/Access_Denied")
		 * .and().logout().logoutSuccessUrl("/");
		 */

		http.authorizeRequests().anyRequest().permitAll().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/login").usernameParameter("username").passwordParameter("password").and()
				.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
				.tokenValiditySeconds(60*2).key("Spitter-RememberMe").and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied")
				.and().logout().logoutSuccessUrl("/");
		
		//Autorization for all Spitter
		http.authorizeRequests().and().formLogin().and().httpBasic().realmName(Spitter.class.getName());

		// After a successful Login process, the user is redirected to a page –
		// which by default is the root of the web application.
		http.formLogin().defaultSuccessUrl("/spitter");

		http.requiresChannel().anyRequest().requiresInsecure();

		// http.requiresChannel().antMatchers("/").requiresInsecure();

//		http.authorizeRequests().antMatchers("/list").access("hasRole('DB') or hasRole('ADMIN')");
		
		// DA RIVEDERE
		http.authorizeRequests().antMatchers("/spitters/{username}").authenticated();
		http.authorizeRequests().antMatchers("/spitters")
				.access("hasRole('USER') or hasRole('ADMIN') or hasRole('DB')");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/spittle").access("hasRole('ADMIN')");

		// http://www.baeldung.com/spring-channel-security-https
		http.sessionManagement().sessionFixation().none();

		// http.csrf().disable();

		// http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
		// http.authorizeRequests().antMatchers("/**").hasRole("USER");

		logger.info("{}::configure view permit", this.getClass().getSimpleName());
		logger.info("{} initialized", this.getClass().getSimpleName());

		/**
		 * http.authorizeRequests().antMatchers("/", "/list")
		 * .access("hasRole('USER') or hasRole('ADMIN') or hasRole('DBA')")
		 * .antMatchers("/newuser/**",
		 * "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
		 * .access("hasRole('ADMIN') or
		 * hasRole('DBA')").and().formLogin().loginPage("/login")
		 * .loginProcessingUrl("/login").usernameParameter("ssoId").passwordParameter("password").and()
		 * .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
		 * .tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
		 */

		/**
		 * 
		 */
	}

	/**
	 * AuthenticationManagerBuilder SecurityBuilder used to create an
	 * AuthenticationManager. Allows for easily building in memory
	 * authentication, LDAP authentication, JDBC based authentication, adding
	 * UserDetailsService, and adding AuthenticationProvider's.
	 */
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(getSpitterDetailService());
		auth.authenticationProvider(getAuthenticationProvider());
		logger.info("ConfigureGlobalSecurity user permit initialized");
	}

	/**
	 * DaoAuthenticationProvider An AuthenticationProvider implementation that
	 * retrieves user details from a UserDetailsService.
	 */
	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(getSpitterDetailService());
		logger.info("UserDetailsService initialized");
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		logger.info("Password encriptinig");
		return authenticationProvider;
	}

	static {
		logger.info("************************End - WebSecurityConfig************************");
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		logger.info("BCryptPasswordEncoder  initialized");
		return new BCryptPasswordEncoder();
	}

	// Return persistentToken for RemeberMe service
	// the project is using the RememberMe with JdbcTokenRepositoryImpl
	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", getSpitterDetailService(), getTokenRepository());
		logger.info("PersistentTokenService  initialized");
		return tokenBasedservice;
	}

}