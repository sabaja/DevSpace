package com.mvc.spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.lang.invoke.MethodHandles;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mvc.spittr.service.backup.SpittleRepository;
import com.mvc.spittr.web.util.RequestUtilFacade;

/**
 * 
 * @author sabaja a controller class that handles requests for / and ren ders
 *         the application’s home page. LoginController , shown in the following
 *         listing, is an example of what might be the simplest possible Spring
 *         MVC controller class.
 *
 */
// @Controller Declared to be a controller,the component scanner will
// automatically pick up LoginController and declare it as a bean in
// the Spring application context.

// Any time there’s a class-level @RequestMapping on a controller class, it
// applies to
// all handler methods in the controller.
@Controller
@SessionAttributes("roles")
public class LoginController {

	@SuppressWarnings("unused")
	private SpittleRepository spittleRepository;
	// This pattern can be cut and pasted across classes.
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	// https://docs.oracle.com/javaee/7/api/
	// @Autowired
	private AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	// breaking breaking circular dependencies.
	// http://kim.saabye-pedersen.org/2012/11/breaking-circular-dependency-in-spring.html
	// @Autowired
	public LoginController(Provider<AuthenticationTrustResolver> authenticationTrustResolverProvider) {
		super();
		this.authenticationTrustResolver = authenticationTrustResolverProvider.get();
		logger.info("Using Provider<T> for trust-resolver-provider to break circula dependecies");

	}

	// @Autowired
	// public LoginController(SpittleRepository spittleRepository) {
	// super();
	//
	// logger.info("{} Constructor call", LocalDateTime.now());
	// this.spittleRepository = spittleRepository;
	// }

	// root call welcome page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcomePage(HttpServletRequest request, HttpServletResponse response) {
		// logger.info("{} request called ",
		// RequestUtilFacade.getCurrentUri(request).toString());
		return "welcome";

	}

	// Given the way you configured InternalResourceViewResolver , the view name
	// “home” will be resolved as a JSP at / WEB-INF /views/home.jsp. For now,
	// you’ll keep the Spittr application’s home page rather basic, as shown
	// next.
	@RequestMapping(value = "/spitter", method = RequestMethod.GET)
	public String home(HttpServletRequest request, HttpServletResponse response) {
		// logger.info("{} request called ",
		// RequestUtilFacade.getCurrentUri(request).toString());
		// logger.info("{} /Home called", LocalDateTime.now());
		return "home";
	}

	// http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (this.isCurrentAuthenticationAnonymous()) {
			logger.info("login ok");
			return "login";
		} else {
			logger.info("redirect:/");
			return "redirect:/";
		}
	}

	// public WebUserFacilityAuthentication getFacility() {
	// return facility;
	// }
	// @Autowired
	// public void setFacility(WebUserFacilityAuthentication facility) {
	// this.facility = facility;
	// }
	private String getPrincipal() {
		String userName = null;
		// The identity of the principal being authenticated. In the case of an
		// authentication request with username and password, this would be the
		// username. Callers are expected to populate the principal for an
		// authentication request.
		// The AuthenticationManager implementation will often return an
		// Authentication containing richer information as the principal for use
		// by the application. Many of the authentication providers will create
		// a UserDetails object as the principal.
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
			logger.info("Auth valaue: {}", userName);
		} else {
			userName = principal.toString();
			logger.info("Auth valaue: {}", userName);
		}
		return userName;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in],
	 * else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean flag;

		if (Objects.isNull(authenticationTrustResolver)) {
			authenticationTrustResolver = new AuthenticationTrustResolverImpl();
			flag = authenticationTrustResolver.isAnonymous(authentication);
			logger.info("AuthenticationTrustResolver is null flag value: {}", flag);
		} else {
			flag = authenticationTrustResolver.isAnonymous(authentication);
			logger.info("AuthenticationTrustResolver is not null value: {}", flag);
		}
		return flag;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response,
			// auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
	}

	// public AuthenticationTrustResolver getAuthenticationTrustResolver() {
	// return authenticationTrustResolverProvider;
	// }
	//
	// @Autowired
	// public void setAuthenticationTrustResolver(AuthenticationTrustResolver
	// authenticationTrustResolverProvider) {
	// this.authenticationTrustResolver = authenticationTrustResolverProvider;
	// }

}
