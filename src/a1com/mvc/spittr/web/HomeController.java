package a1com.mvc.spittr.web;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import a1com.mvc.spittr.DAO.SpittleRepository;
import a1com.mvc.spittr.web.util.RequestUtilFacade;

/**
 * 
 * @author sabaja a controller class that handles requests for / and ren ders
 *         the application’s home page. HomeController , shown in the following
 *         listing, is an example of what might be the simplest possible Spring
 *         MVC controller class.
 *
 */
// @Controller Declared to be a controller,the component scanner will
// automatically pick up HomeController and declare it as a bean in
// the Spring application context.

// Any time there’s a class-level @RequestMapping on a controller class, it
// applies to
// all handler methods in the controller.
@Controller
@RequestMapping({ "/homepage" })
public class HomeController {

	@SuppressWarnings("unused")
	private SpittleRepository spittleRepository;
	// This pattern can be cut and pasted across classes.
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public HomeController() {
		super();
	}

	@Autowired
	public HomeController(SpittleRepository spittleRepository) {
		super();
		
		logger.info("{} Constructor call", LocalDateTime.now());
		this.spittleRepository = spittleRepository;
	}

	// Given the way you configured InternalResourceViewResolver , the view name
	// “home” will be resolved as a JSP at / WEB-INF /views/home.jsp. For now,
	// you’ll keep the Spittr application’s home page rather basic, as shown
	// next.
	@RequestMapping(method = GET)
	public String home(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
//		logger.info("{} /Home called", LocalDateTime.now());
		return "home";
	}
}
