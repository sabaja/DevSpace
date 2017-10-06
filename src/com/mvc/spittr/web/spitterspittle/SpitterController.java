package com.mvc.spittr.web.spitterspittle;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.eclipse.jetty.util.MultiPartInputStreamParser.MultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mvc.spittr.constants.SpitterConsts;
import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.entity.Spittle;
import com.mvc.spittr.service.SpitterService;
import com.mvc.spittr.service.backup.SpitterRepository;
import com.mvc.spittr.web.util.RequestUtilFacade;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

	// This pattern can be cut and pasted across classes.
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final String registerForm = "registerForm_sf_2";
	private final String profile = "profile";
	private SpitterRepository spitterRepository;

	@Autowired
	private SpitterService service;

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
		logger.info("showRegistration() called view={}", registerForm);
		model.addAttribute(new Spitter());
		return registerForm;
	}

	// Multipart picture download @RequestPart example:
	// http://www.journaldev.com/2573/spring-mvc-file-upload-example-single-multiple-files
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String processRegistration(@RequestPart("profilePicture") MultipartFile file, @Valid Spitter spitter,
			Errors errors, RedirectAttributes redirectAttributes, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		if (errors.hasErrors() || file.isEmpty()) {
			logger.info("errors: {}", errors.getAllErrors());
			// flash attribute
			redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
			logger.info("errors: {}", "No file selected");
			return registerForm;
		}

		service.save(spitter);
		// spitterRepository.save(spitter);
 
		logger.info("spitter was registered value= {}", spitter.toString());

		// saving multipartFile
		this.saveMultipartFile(file);

		// // Redirecting with URL templates but it needs saving spitter in
		// persistence mode
		// model.addAttribute("username", spitter.getUsername());
		// model.addAttribute("password", spitter.getPassword());
		// model.addAttribute("firstname", spitter.getFirstName());
		// model.addAttribute("lastname", spitter.getLastName());
		// return "redirect:/spitter/{username}";
		redirectAttributes.addFlashAttribute("spitter", spitter);
		redirectAttributes.addFlashAttribute("username", spitter.getUsername());

		// return "redirect:/spitter/{username}";
		return "redirect:/spitter/" + spitter.getUsername();
	}

	@RequestMapping(value = "/{username}", method = RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		// model.addAttribute("spitter",spitterRepository.findByUserName(username));
		Spitter spitter = service.findByUserName(username);
		model.addAttribute("spitter", spitter);
		logger.info("{} request called ", RequestUtilFacade.getCurrentUri(request).toString());
		logger.info("spitter username value= {}", username);

		return profile;
	}

	/**
	 * Saving multipart using Files.write​(Path path, byte[] bytes,
	 * OpenOption... options)
	 */

	private void saveMultipartFile(MultipartFile file) {
		try {
			String fileName = file.getOriginalFilename();
			byte[] bytes = file.getBytes();
			Path path = Paths.get(SpitterConsts.DOWNLOAD_PATH.getStr() + File.separator + fileName);
			logger.info("file name: {}, file size: {}, file path: {}", fileName, bytes.length, path.toString());
			// CREATE
			Files.write(path, bytes, StandardOpenOption.CREATE);
			logger.info("message", "You successfully uploaded '" + file.getOriginalFilename() + "'");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}