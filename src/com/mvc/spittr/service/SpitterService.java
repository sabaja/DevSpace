package com.mvc.spittr.service;

import java.util.List;

import com.mvc.spittr.entity.Spitter;

public interface SpitterService {
	public Spitter findByUserName(String username);
	public Spitter findBySsoId(String ssoId);
	public Spitter getPersonById(Long id);
	public void save(Spitter spitter);
	public void updateSpitter(Spitter spitter);
	public List<Spitter> listSpitters();
	public void removePerson(Long id);
}
