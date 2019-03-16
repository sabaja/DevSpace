package com.mvc.spittr.service.backup;

import com.mvc.spittr.entity.Spitter;

public interface SpitterRepository {
	public Spitter findByUserName(String username);
	public void save(Spitter spitter);

}
