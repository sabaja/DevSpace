package com.mvc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mvc.spittr.web.LoginController;

public class ControllerHomeTest {
	
	@Test
	public void homeTest(){
		LoginController home = new LoginController();
		assertEquals("home", home.home(null,null));
	}

}
