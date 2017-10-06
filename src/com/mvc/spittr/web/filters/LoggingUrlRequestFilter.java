package com.mvc.spittr.web.filters;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "logginFilter", urlPatterns = { "/*" })
public class LoggingUrlRequestFilter implements Filter {

	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void init(FilterConfig filter) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		logger.info("[" + this.getClass().getName() + "]: " +  LocalDateTime.now() + " - " 
				+ servletRequest.getRequestURI());
		// System.out.println(LocalDateTime.now() + " - " + prefix + " " +
		// servletRequest.getRequestURI());
		
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
