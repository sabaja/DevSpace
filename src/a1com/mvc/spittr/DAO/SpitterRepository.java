package a1com.mvc.spittr.DAO;

import a1com.mvc.spittr.pojo.Spitter;

public interface SpitterRepository {
	public Spitter findByUserName(String username);
	public void save(Spitter spitter);

}
