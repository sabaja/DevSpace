package com.mvc.spittr.dao;

import java.util.List;

import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.entity.Spittle;

/**
 * http://www.journaldev.com/3531/spring-mvc-hibernate-mysql-integration-crud-example-tutorial
 * https://www.boraji.com/spring-4-hibernate-5-integration-example-with-zero-xml
 * @author sabaja
 *
 */
public interface SpitterDao {
	public Spitter findByUserName(String username);
	public Spitter getPersonById(Long id);
	public void save(Spitter spitter);
	public void updateSpitter(Spitter spitter);
	public List<Spitter> listSpitters();
	public void removePerson(Long id);
	public void insertSpittle(Spittle spittle);
}
