package com.mvc.test;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import com.mvc.spittr.web.spitterspittle.SpitterController;

//import com.mvc.spittr.web.SpitterController;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

public class SpitterTest {
	@Test
	public void shouldShowRegistration() throws Exception {
		SpitterController controller = new SpitterController();
		MockMvc mockMvc = standaloneSetup(controller).build();
		mockMvc.perform(get("/spitter/registration")).andExpect(view().name("registerForm"));
		MockMvc mockMvc2 = MockMvcBuilders.standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/registerForm.jsp")).build();
	}

}
