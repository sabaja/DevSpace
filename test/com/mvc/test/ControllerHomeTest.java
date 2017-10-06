package com.mvc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mvc.spittr.web.HomeController;

public class ControllerHomeTest {
	
	@Test
	public void homeTest(){
		HomeController home = new HomeController();
		assertEquals("home", home.home(null,null));
	}

}
