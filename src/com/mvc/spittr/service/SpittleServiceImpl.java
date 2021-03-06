package com.mvc.spittr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.spittr.dao.SpittleDao;
import com.mvc.spittr.entity.Spittle;

@Service("spittleServiceImpl")
@Transactional
public class SpittleServiceImpl implements SpittleService {

	@Autowired
	private SpittleDao spittleDao;
	
	@Override
	public List<Spittle> findSpittles() {
		return spittleDao.findSpittles();
	}
	
	@Override
	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spittle findSpittleById(long id) {
		return spittleDao.findSpittleById(id);
	}



}
