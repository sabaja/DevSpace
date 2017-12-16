package com.mvc.spittr.dao;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.entity.Spittle;

@Repository("spittleDAOImpl")
public class SpittleDAOImpl implements SpittleDao{
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private SessionFactory sessionFactory;


	@Override
	public List<Spittle> findSpittles() {
		Session session = sessionFactory.openSession();
		return session.createQuery("FROM Spittle").list();
	}	
	
	@Override
	public List<Spittle> findSpittles(long max, int count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Spittle findSpittleById(long id) {
		Session session = sessionFactory.getCurrentSession();
		Spittle spittle = session.load(Spittle.class, id);
		logger.info("Spittle loaded successfully, Spittle details=" + spittle);
		return spittle;
	}


}
