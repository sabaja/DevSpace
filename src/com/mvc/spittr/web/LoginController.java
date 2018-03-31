package com.mvc.spittr.web;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Objects;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.service.SpitterService;
import com.mvc.spittr.service.SpitterServiceImpl;
import com.mvc.spittr.service.backup.SpittleRepository;
import com.mvc.spittr.web.util.WebFacilities;

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
@SessionAttributes(names = { "roles", "users" })
public class LoginController {

	@SuppressWarnings("unused")
	private SpittleRepository spittleRepository;
	// This pattern can be cut and pasted across classes.
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	// https://docs.oracle.com/javaee/7/api/
	// @Autowired
	private AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	private PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	private SpitterService spitterService;

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
	public String welcomePage(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		// logger.info("{} request called ",
		// RequestUtilFacade.getCurrentUri(request).toString());
		return "welcome";

	}

	// http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
	/**
	 * This method handles login GET requests. If users is already logged-in and
	 * tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		if (this.isCurrentAuthenticationAnonymous()) {
			logger.info("Is Current Authentication Anonymous? {}", this.isCurrentAuthenticationAnonymous());
			return "login";
		} else {
			logger.info("login requested but redirect:/spitter");
			return "redirect:/spitter";
		}
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(HttpSession session, ModelMap model) {
		if (!Objects.isNull(spitterService)) {
			logger.info("spitterService is null? {}", Objects.isNull(spitterService));
		}

		List<Spitter> users = spitterService.listSpittersWithCriteria();
		if (Objects.isNull(users)) {
			// users.stream().forEach(s -> System.out.println(s.toString()));
			/*
			 * String sessionUserParam = (String)
			 * session.getAttribute("users").toString(); 
			 * if (sessionUserParam.equals(null) || "".equals(sessionUserParam)) {
			 */
			
			logger.info("Users are null? {}", Objects.isNull(users));
		}
		model.addAttribute("users", users);
		model.addAttribute("loggedinuser", WebFacilities.getPrincipal());

		return "userslist";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		logger.info("Before Logout - authentication_{} ", auth.toString());
		if (auth != null) {
			// new SecurityContextLogoutHandler().logout(request, response,
			// auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
			logger.info("After Logout authentication_{} ", auth.toString());
		}
		// return "redirect:/login?logout";
		return "redirect:/";
	}

	@RequestMapping(value ="/access_denied", method = RequestMethod.GET)
	public String accessDenied(){
		return "accessDenied";
	}
	
	
	/**
	 * @return the authenticationTrustResolver
	 */
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return authenticationTrustResolver;
	}

	/**
	 * @param authenticationTrustResolver
	 *            the authenticationTrustResolver to set
	 */
	@Autowired
	public void setAuthenticationTrustResolver(AuthenticationTrustResolver authenticationTrustResolver) {
		this.authenticationTrustResolver = authenticationTrustResolver;
	}

	/**
	 * This method returns true if users is already authenticated [logged-in],
	 * else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean flag = authenticationTrustResolver.isAnonymous(authentication);
		logger.info("isCurrentAuthenticationAnonymous() flag value: {} and credential_{}", flag,
				authentication.getCredentials());
		/*
		 * if (Objects.isNull(authenticationTrustResolver)) {
		 * authenticationTrustResolver = new AuthenticationTrustResolverImpl();
		 * flag = authenticationTrustResolver.isAnonymous(authentication);
		 * 
		 * logger.
		 * info("\nAuthenticationTrustResolver is null flag value: {} and credential_{}"
		 * , flag, authentication.getCredentials()); } else { flag =
		 * authenticationTrustResolver.isAnonymous(authentication); logger.
		 * info("\nAuthenticationTrustResolver is not null value: {} and credential_{}"
		 * , flag, authentication.getCredentials()); }
		 */
		return flag;
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

	/**
	 * @return the spitterService
	 */
	
	public SpitterService getSpitterService() {
		return spitterService;
	}

	/**
	 * @param spitterService the spitterService to set
	 */
	public void setSpitterService(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

	// public WebUserFacilityAuthentication getFacility() {
	// return facility;
	// }
	// @Autowired
	// public void setFacility(WebUserFacilityAuthentication facility) {
	// this.facility = facility;
	// }
	/*
	 * private String getPrincipal() { String userName = null; // The identity
	 * of the principal being authenticated. In the case of an // authentication
	 * request with username and password, this would be the // username.
	 * Callers are expected to populate the principal for an // authentication
	 * request. // The AuthenticationManager implementation will often return an
	 * // Authentication containing richer information as the principal for use
	 * // by the application. Many of the authentication providers will create
	 * // a UserDetails object as the principal. Object principal =
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * 
	 * if (principal instanceof UserDetails) { userName = ((UserDetails)
	 * principal).getUsername(); logger.info("Auth valaue: {}", userName); }
	 * else { userName = principal.toString(); logger.info("Auth valaue: {}",
	 * userName); } return userName; }
	 */
	
}
