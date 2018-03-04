package com.mvc.spittr.web.util;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class WebFacilities {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static String getPrincipal() {
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
			userName = "anonymousUser".equals(principal.toString()) ? "anonymous" : "anonymousUser";
			logger.info("Auth valaue: {}", userName);
		}
		return userName;
	}

}
