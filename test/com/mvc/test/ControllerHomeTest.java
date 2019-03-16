package com.mvc.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.mvc.spittr.controller.LoginController;
import com.mvc.spittr.controller.SpitterController;

public class ControllerHomeTest {
	
	@Test
	public void homeTest(){
		SpitterController home = new SpitterController();
		assertEquals("home", home.home(null,null,null));
	}

}
