package com.mvc.spittr.service;

import java.util.List;

import com.mvc.spittr.entity.Spittle;

public interface SpittleService {

	public List<Spittle> findSpittles();
	public List<Spittle> findSpittles(long max, int count);
	public Spittle findSpittleById(long id);
	
}
