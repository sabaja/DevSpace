package com.mvc.spittr.log;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExample {
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());//This pattern can be cut and pasted across classes.

	public static void main(String[] args) {

		logger.info("Info");
		logger.debug("Debug");
		logger.trace("trace");
		logger.error("error");
		logger.warn("warn");

		setTemperature(238);
	}

	public static void setTemperature(Integer temperature) {

		Integer oldT, t = 0;
		oldT = t;
		t = temperature;

		logger.debug("Temperature set to {}. Old temperature was {}.", t, oldT);
//		logger.info("Temperature set to {}. Old temperature was {}.", t, oldT);


		if (temperature.intValue() > 50) {
			logger.info("Temperature has risen above 50 degrees.");
		}
	}
}