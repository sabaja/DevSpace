package com.mvc.spittr.web;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mvc.spittr.entity.Spittle;
import com.mvc.spittr.exceptions.SpittleNotFoundException;
import com.mvc.spittr.service.SpitterService;
import com.mvc.spittr.service.SpittleService;
import com.mvc.spittr.service.backup.SpittleRepository;
import com.mvc.spittr.web.util.RequestUtilFacade;
import com.mvc.spittr.web.util.WebFacilities;

@Controller
@RequestMapping({ "/spittle" })
public class SpittleController {

	@Autowired
	private SpittleRepository spittleRepository;
	@Autowired
	private SpittleService spittleService;
	@Autowired
	private SpitterService spitterService;

	private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;
	// private static final String MAX_LONG_AS_STRING =
	// Long.toString(Long.MAX_VALUE);
	// doesent work
	// https://stackoverflow.com/questions/39157370/java-code-wont-compile-due-to-attribute-must-be-a-constant-expression-error
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());// This

	public SpittleController(SpittleRepository spittleRepository) {
		logger.debug("{} Constructor call", LocalDateTime.now());
		this.spittleRepository = spittleRepository;
	}

	/**
	 * precedent codes
	 * 
	 * @RequestMapping(method = RequestMethod.GET) public String spittles(Model
	 *                        model) { spittleRepository = new
	 *                        SpittleRepositoryImpl();
	 *                        model.addAttribute("spittleList",spittleRepository.findSpittles(Long.MAX_VALUE,
	 *                        20)); return "spittles"; }
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String spittles(ModelMap model, @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "30") int count, HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Spittle> spittles = spittleService.findSpittles();
		model.addAttribute("spittleList", spittles);

		List<Long> spitterIds = spittles.stream().map(Spittle::getId).collect(Collectors.toList());
		if (Objects.isNull(spitterIds)) {
			logger.info("{} List<Long> spitterIds is null", LocalDateTime.now());
		}
		
		model.addAttribute("spitterIds", spitterIds);
		model.addAttribute("loggedinuser", WebFacilities.getPrincipal());
		
		logger.info("{} /spittle called", LocalDateTime.now());
		return "spittles";// name view
	}

	// Other version without model argument
	// @RequestMapping(method = RequestMethod.GET)
	// public List<Spittle> spittles(@RequestParam(value = "max", defaultValue =
	// MAX_LONG_AS_STRING) long max,
	// @RequestParam(value = "count", defaultValue = "20") int count) {
	// return spittleRepository.findSpittles(max, count);
	// }
	// }
	/**
	 * Likewise, if you’d prefer to work with a non-Spring type, you can ask for
	 * a java .util.Map instead of Model . Here’s another version of spittles()
	 * that’s functionally equivalent to the others:
	 * 
	 * @RequestMapping(method=RequestMethod.GET) public String spittles(Map
	 *                                           model) {
	 *                                           model.put("spittleList",
	 *                                           spittleRepository.findSpittles(Long.MAX_VALUE,20));
	 *                                           return "spittles"; }
	 */

	/**
	 * And while we’re on the subject of alternate implementations, here’s
	 * another way to rite the spittles() method:
	 *
	 * @RequestMapping(method=RequestMethod.GET) ublic List<Spittle> spittles()
	 *                                           { return
	 *                                           spittleRepository.findSpittles(Long.MAX_VALUE,20));
	 *                                           }
	 * 
	 *                                           This version is quite a bit
	 *                                           different from the others.
	 *                                           Rather than return a logical
	 *                                           view name and explicitly
	 *                                           setting the model, this method
	 *                                           returns the Spittle list. When
	 *                                           a handler method returns an
	 *                                           object or a collection like
	 *                                           this, the value returned is put
	 *                                           into the model, and the model
	 *                                           key is inferred from its type
	 *                                           (spittleList , as in the other
	 *                                           examples).
	 * 
	 */

	// this is calling http://localhost:8080/Spring-In-Action_WEBApp/spittle/1
	// viene chiamato dopo il link spittle
	@RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
	public String spittle(@PathVariable long spittleId, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		Spittle spittle = spittleService.findSpittleById(spittleId);
		if (spittle == null) {
			logger.info("spittle with id {} is null", spittleId);
			throw new SpittleNotFoundException();
		}
		model.addAttribute(spittle);
		model.addAttribute("loggedinuser", WebFacilities.getPrincipal());
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		logger.info("{} single view is called with param {} ", LocalDateTime.now(), spittleId);
		return "spittle";
	}

	/**
	 * Things to Consider:
	 * 
	 * Check your request header and see if it's submitting to floors/upload. if
	 * not try to add action="floors/upload" property in your form tag.
	 * 
	 * Try to change your controller to (without the path) "/save" to "save"
	 * https://stackoverflow.com/questions/34420980/spring-mvc-http-status-405-request-method-post-not-supported
	 * 
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "exception", method = RequestMethod.POST)
	public @ResponseBody String exception(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		String msg = String.format("Test exception from class: %s", this.getClass().getSimpleName());
		logger.info("{}", msg);
		throw new RuntimeException(msg);
		// return model.toString();
	}
}