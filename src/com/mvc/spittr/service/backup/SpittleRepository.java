package com.mvc.spittr.service.backup;

import java.util.List;

import com.mvc.spittr.entity.Spittle;



/**
 * 
 * @author sabaja the findSpittles() method takes two parameters. The max
 *         parameter is a Spittle ID that represents the maximum ID of any
 *         Spittle that should be returned. As for the count parameter, it
 *         indicates how many Spittle objects to return. In order to get the 20
 *         most recent Spittle objects, you can call findSpittles() like this:
 *         List<Spittle> recent = spittleRepository.findSpittles(Long.MAX_VALUE,
 *         20);
 *
 */
public interface SpittleRepository {
	
	public List<Spittle> findSpittles(long max, int count);
	public Spittle findSpittleById(long id);
	
	
}