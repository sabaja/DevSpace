package com.mvc.spittr.controller.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;

import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@WebServlet(name = "GenericServletDemoServlet", 
//    urlPatterns = { "/generic" },
//    initParams = {
//        @WebInitParam(name="admin", value="Harry Taciak"),
//        @WebInitParam(name="email", value="admin@example.com")
//    }
//)
public class GenericServletDemoServlet extends GenericServlet {
    
    private static final long serialVersionUID = 62500890L;
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	static{
		logger.info("************************Start - GenericServletDemoServlet************************");
	}
    @Override
    public void service(ServletRequest request, 
            ServletResponse response)
            throws ServletException, IOException {
        ServletConfig servletConfig = getServletConfig();
        String admin = servletConfig.getInitParameter("admin");
        String email = servletConfig.getInitParameter("email");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.print("<html><head></head><body>" + 
                "Admin:" + admin + 
                "<br/>Email:" + email +
                "</body></html>");
    }
    
    static{
		logger.info("************************End - GenericServletDemoServlet************************");
	}
}