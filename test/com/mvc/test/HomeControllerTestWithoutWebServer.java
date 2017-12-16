package com.mvc.test;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.mvc.spittr.web.LoginController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class HomeControllerTestWithoutWebServer {

	@Test
	public void testHomePage() throws Exception {
		LoginController controller = new LoginController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/")).andExpect(view().name("home"));
	}

}
