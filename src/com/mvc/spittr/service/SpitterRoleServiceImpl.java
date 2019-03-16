package com.mvc.spittr.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.spittr.dao.SpitterRoleDao;
import com.mvc.spittr.entity.SpitterRole;

@Service("spitterRoleServiceImpl")
@Transactional
public class SpitterRoleServiceImpl implements SpitterRoleService {

	@Autowired
	private SpitterRoleDao roleDao;
	
	@Override
	public List<SpitterRole> findAll() {
		roleDao.findAll();
		return null;
	}

	@Override
	public SpitterRole findByType(String type) {
		roleDao.findByType(type);
		return null;
	}

	@Override
	public SpitterRole findById(Long id) {
		roleDao.findById(id);
		return null;
	}

}
