package a1com.mvc.spittr.web;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import a1com.mvc.spittr.DAO.SpitterRepository;
import a1com.mvc.spittr.pojo.Spitter;
import a1com.mvc.spittr.web.util.RequestUtilFacade;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	// This pattern can be cut and pasted across classes.
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final String registerForm = "registerForm_sf_2";
	private final String profile = "profile";
	private SpitterRepository spitterRepository;

	public SpitterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Autowired
	public SpitterController(SpitterRepository spitterRepository) {
		super();
		this.spitterRepository = spitterRepository;
	}

	// The showRegistrationForm() method’s @RequestMapping annotation, along
	// with the
	// class-level @RequestMapping annotation, declares that it will handle HTTP
	// GET requests
	// for /spitter/register. It’s a simple method, taking no input and only
	// returning a logi-
	// cal view named registerForm .

	/**
	 * The <sf:form> tag renders an HTML <form> tag. But it also sets some
	 * context around a model object designated in the commandName attribute.
	 * Properties on the model object will be referenced in the other
	 * form-binding tags you use. In the preceding code, you set commandName to
	 * spitter . Therefore, there must be an object in the model whose key is
	 * spitter , or else the form won’t be able to render (and you’ll get JSP
	 * errors). That means you need to make a small change to Spitter-
	 * Controller to ensure that a Spitter object is in the model under the
	 * spitter key:
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String showRegistration(HttpServletRequest request, HttpServletResponse response, Model model) {
		// https://stackoverflow.com/questions/8504258/spring-3-mvc-accessing-httprequest-from-controller
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		logger.info("{} showRegistration() called view={}", LocalDateTime.now(), registerForm);
		model.addAttribute(new Spitter());
		return registerForm;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String processRegistration(@Valid Spitter spitter, Errors errors, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		if (errors.hasErrors()) {
			logger.info("errors: {}", errors.getAllErrors());
			return registerForm;
		}

		spitterRepository.save(spitter);
		logger.info("spitter was registered value= {}", spitter.toString());

		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		model.addAttribute(spitterRepository.findByUserName(username));
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		logger.info("spitter username value= {}", username);

		return profile;
	}

}