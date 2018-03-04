package com.mvc.spittr.service;

import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.spittr.dao.SpitterDAOImpl;
import com.mvc.spittr.dao.SpitterDao;
import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.entity.Spittle;

@Service("spitterServiceImpl")
@Transactional
public class SpitterServiceImpl implements SpitterService {
	
	@Autowired
	private SpitterDao spitterDao;
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	
	@Override
	public Spitter findByUserName(String username) {
		logger.info("Inside SpitterDaoImpl.findByUserName");
		return spitterDao.findByUserName(username);
	}

	@Override
	public void save(Spitter spitter) {
		spitterDao.save(spitter);
	}

	@Override
	public Spitter getPersonById(Long id) {
		return spitterDao.getPersonById(id);
	}

	@Override
	public void updateSpitter(Spitter spitter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Spitter> listSpitters() {
		return spitterDao.listSpitters();
	}

	@Override
	public void removePerson(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Spitter findBySsoId(String ssoId) {
		return spitterDao.findBySsoId(ssoId);
	}

	/**
	 * @return the spitterDao
	 */
	public SpitterDao getSpitterDao() {
		return spitterDao;
	}

	/**
	 * @param spitterDao the spitterDao to set
	 */
	public void setSpitterDao(SpitterDao spitterDao) {
		this.spitterDao = spitterDao;
	}

	@Override
	public List<Spitter> listSpittersWithCriteria() {
		return spitterDao.listSpittersWithCriteria();
	}



}
