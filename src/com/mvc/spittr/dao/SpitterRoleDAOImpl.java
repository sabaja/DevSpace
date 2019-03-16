package com.mvc.spittr.dao;

import java.lang.invoke.MethodHandles;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mvc.spittr.entity.SpitterRole;

@Repository("spitterRoleDAOImpl")
public class SpitterRoleDAOImpl extends AbstractDao<Long, SpitterRole> implements SpitterRoleDao{
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public SpitterRole findById(Long id) {
		SpitterRole spitterRole = getByKey(id);
		logger.info("Found Role by id={}", spitterRole);
		return spitterRole;
	}

	public SpitterRole findByType(String role) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("role", role));
		SpitterRole spitterRole = (SpitterRole) crit.uniqueResult();
		logger.info("Found Role by role={}", spitterRole);
		return spitterRole;
	}
	
	@SuppressWarnings("unchecked")
	public List<SpitterRole> findAll(){
		Criteria crit = createEntityCriteria();
		crit.addOrder(Order.asc("role"));
		logger.info("Roles are all found");		
		return (List<SpitterRole>)crit.list();
	}

}
