package a1com.mvc.spittr.web;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import a1com.mvc.spittr.DAO.SpittleRepository;
import a1com.mvc.spittr.web.util.RequestUtilFacade;

@Controller
@RequestMapping({ "/spittle" })
public class SpittleController {

	@Autowired
	private SpittleRepository spittleRepository;
	private static final String MAX_LONG_AS_STRING = "" + Long.MAX_VALUE;
	// private static final String MAX_LONG_AS_STRING =
	// Long.toString(Long.MAX_VALUE);
	// doesent work
	// https://stackoverflow.com/questions/39157370/java-code-wont-compile-due-to-attribute-must-be-a-constant-expression-error
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());// This
																										// pattern
																										// can
																										// be
																										// cut
																										// and
																										// pasted
																										// across
																										// classes.
	private final String spittles = "spittles";

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
	public String spittles(Model model, @RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
			@RequestParam(value = "count", defaultValue = "30") int count, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute("spittleList", spittleRepository.findSpittles(max, count));
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		logger.info("{} /spittle called with params: count={}, value={}, view={}", LocalDateTime.now(), count, max,
				spittles);
		return spittles;// name view
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
		model.addAttribute(spittleRepository.findSpittleById(spittleId));
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		logger.info("{} single view is called with param {} ", LocalDateTime.now(), spittleId);
		return "spittle";
	}

}