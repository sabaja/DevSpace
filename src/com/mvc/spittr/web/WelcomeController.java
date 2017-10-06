package com.mvc.spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.lang.invoke.MethodHandles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mvc.spittr.web.util.RequestUtilFacade;


@Controller
@RequestMapping("/")
public class WelcomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@RequestMapping(method = GET)
	public String welcomePage(HttpServletRequest request, HttpServletResponse response){
//		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		return "welcome";
		
	}
}
