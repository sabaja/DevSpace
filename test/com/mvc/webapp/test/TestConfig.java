package com.mvc.webapp.test;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.mvc.spittr.controller.config" })
public class TestConfig {
}
