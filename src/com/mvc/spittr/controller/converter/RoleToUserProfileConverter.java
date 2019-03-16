package com.mvc.spittr.controller.converter;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.mvc.spittr.entity.SpitterRole;
import com.mvc.spittr.service.SpitterRoleService;

/**
 * http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
 * 
 * The main highlight of this configuration is RoleToUserProfileConverter. It
 * will take care of mapping the individual userProfile id’s on view to actual
 * UserProfile Entities in database. This is a converter class used in views to
 * map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, SpitterRole> {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	static{
		logger.info("************************Start RoleToUserProfileConverter************************");
	}

	@Autowired
	private SpitterRoleService spitterRoleService;

	@Override
	public SpitterRole convert(Object element) {
		Long id = Long.parseLong((String) element);
		SpitterRole spitterRole = spitterRoleService.findById(id);
		logger.info("Profile : {}", spitterRole.toString());
		return spitterRole;
	}
	
	static{
		logger.info("************************End RoleToUserProfileConverter************************");
	}


}
