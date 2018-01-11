package com.mvc.spittr.service;

import java.util.List;

import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.entity.Spittle;

public interface SpitterService {
	public Spitter findByUserName(String username);
	public Spitter findBySsoId(String ssoId);
	public Spitter getPersonById(Long id);
	public void save(Spitter spitter);
	public void updateSpitter(Spitter spitter);
	public List<Spitter> listSpitters();
	public List<Spittle> listSpittles(Long id);
	public void removePerson(Long id);
}
