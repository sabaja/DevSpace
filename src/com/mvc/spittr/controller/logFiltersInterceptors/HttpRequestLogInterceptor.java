package com.mvc.spittr.controller.logFiltersInterceptors;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * Duration gaiaBorn =
 * Duration.between(Instant.parse("2013-01-07T03:45:00.00Z"), Instant.now());
 * 
 * @author sabaja http://www.baeldung.com/spring-http-logging
 */
@Component
public class HttpRequestLogInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private Instant start, end;

	static {
		logger.info("HttpRequestLogInterceptor initialized");
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		start = Instant.now();
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		logger.info(LocalDateTime.now() + " - called url:" + servletRequest.getRequestURI());
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		end = Instant.now();

//		logger.info(LocalDateTime.now() + " - elapsed time mill: " + Duration.between(start, end).toMillis());
	}
}
