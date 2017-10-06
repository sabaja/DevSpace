package com.mvc.spittr.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.spittr.dao.SpitterDAOImpl;
import com.mvc.spittr.dao.SpitterDao;
import com.mvc.spittr.entity.Spitter;

@Service
public class SpitterServiceImpl implements SpitterService {
	
	@Autowired
	SpitterDao spitterDao;
	
	@Transactional(readOnly = true)
	@Override
	public Spitter findByUserName(String username) {
		return spitterDao.findByUserName(username);
	}

	
	@Transactional
	@Override
	public void save(Spitter spitter) {
		spitterDao.save(spitter);
	}

	@Override
	public Spitter getPersonById(Long id) {
		spitterDao.getPersonById(id);
		return null;
	}

	@Override
	public void updateSpitter(Spitter spitter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Spitter> listSpitters() {
		spitterDao.listSpitters();
		return null;
	}

	@Override
	public void removePerson(Long id) {
		// TODO Auto-generated method stub
		
	}

}
