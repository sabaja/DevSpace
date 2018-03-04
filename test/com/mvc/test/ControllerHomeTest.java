package com.mvc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mvc.spittr.web.LoginController;
import com.mvc.spittr.web.SpitterController;

public class ControllerHomeTest {
	
	@Test
	public void homeTest(){
		SpitterController home = new SpitterController();
		assertEquals("home", home.home(null,null,null));
	}

}
