package com.mvc.spittr.service;

import java.util.List;

import com.mvc.spittr.entity.SpitterRole;

public interface SpitterRoleService {

	List<SpitterRole> findAll();
	
	SpitterRole findByType(String type);
	
	SpitterRole findById(Long id);
	
}
