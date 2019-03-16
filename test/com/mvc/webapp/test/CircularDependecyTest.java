package com.mvc.webapp.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestConfig.class })
public class CircularDependecyTest {

	@Test
	public void givenCircularDependency_whenConstructorInjection_thenItFails() {
		// Empty test; we just want the context to load
	}

}
