package com.mvc.spittr.dao;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.entity.Spittle;

/**
 * 
 * @author sabaja
 *         https://www.boraji.com/spring-4-hibernate-5-integration-example-with-zero-xml
 *
 */
@Repository("spitterDAOImpl")
public class SpitterDAOImpl implements SpitterDao {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Spitter findByUserName(String username) {
		Session session = sessionFactory.openSession();
		// https://stackoverflow.com/questions/9954590/hibernate-error-querysyntaxexception-users-is-not-mapped-from-users
		/**
		 * For example: your bean class name is UserDetails
		 * 
		 * Query query = entityManager. createQuery("Select UserName from
		 * **UserDetails** "); You do not give your table name on the Db. give
		 * you bean class name.
		 */
		@SuppressWarnings("unchecked")
		TypedQuery<Spitter> query = (TypedQuery<Spitter>) session
				.createQuery("FROM Spitter WHERE USERNAME = :username");
		query.setParameter("username", username);
		Spitter spitter = query.getSingleResult();
		logger.info("Spitter found successfully, Spitter details=" + spitter);
		return spitter;
	}

	@Override
	public Spitter getPersonById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Spitter spitter = session.load(Spitter.class, id);
		logger.info("Person loaded successfully, Person details=" + spitter);
		return spitter;
	}

	@Override
	public void save(Spitter spitter) {
		//Da togliere
//		spitter.setSsoId("ssoId");
		logger.info("employee before saved: {}", spitter.toString());
		sessionFactory.getCurrentSession().save(spitter);
		logger.info("employee after saved: {}", spitter.toString());
		// session.getTransaction().commit();
		// logger.info("tx commit");

	}

	@Override
	public Spitter findBySsoId(String ssoId) {
		logger.info("SSO : {}", ssoId);
		Session session = sessionFactory.openSession();
		@SuppressWarnings("deprecation")
		Criteria crit = session.createCriteria(Spitter.class);
		crit.add(Restrictions.eq("ssoId", ssoId));
		Spitter spitter = (Spitter) crit.uniqueResult();
		if (spitter != null) {
			Hibernate.initialize(spitter.getRoles());
		}
		return spitter;
	}

	@Override
	public void updateSpitter(Spitter spitter) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Spitter> listSpitters() {
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Spitter> spitters = (List<Spitter>) session.createQuery("from Spitter").list();
		logger.info("Listed spitters");
		return spitters;
	}

	@Override
	public void removePerson(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertSpittle(Spittle spittle) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Spittle> listSpittles(Long id) {
		String sql = "from Spittle where spitter_id = :id";
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Spittle> spittles = (List<Spittle>)session.createQuery(sql).setParameter("id", id).list();
		logger.info("Listed spitters by id");
		return spittles;
	}

}
