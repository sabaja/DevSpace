package com.mvc.spittr.dao;

import java.util.List;

import com.mvc.spittr.entity.SpitterRole;


public interface SpitterRoleDao {

	List<SpitterRole> findAll();
	
	SpitterRole findByType(String type);
	
	SpitterRole findById(Long id);
}
