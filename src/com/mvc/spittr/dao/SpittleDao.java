package com.mvc.spittr.dao;

import java.util.List;

import com.mvc.spittr.entity.Spittle;

public interface SpittleDao {
	public List<Spittle> findSpittles();
	public List<Spittle> findSpittles(long max, int count);
	public Spittle findSpittleById(long id);

}
