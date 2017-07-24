package a1com.mvc.spittr.DAO;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import a1com.mvc.spittr.pojo.Spittle;

@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

	//This pattern can be cut and pasted across classes.
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Override
	public List<Spittle> findSpittles(long max, int count) {
		List<Spittle> spittles = null;
		if(count != 0 && count >= 1){
			logger.info("{} findSpittle({},{})", LocalDateTime.now(), max, count);
			spittles = new ArrayList<>(count);
			for(int i = count-1, e = 1; i >= 0; i--, e++){
				Spittle spittle  = new Spittle();
				spittle.setMessage("message " + spittle.getId());
				spittle.setLatitude(Double.doubleToLongBits(rands(max,e)));
				spittle.setLongitude(Double.doubleToLongBits(rands(max,i)));
				spittles.add(spittle);
				logger.info("{} spittle created with id={}", LocalDateTime.now(), spittle.getId());
			}
		}else{
			logger.info("{} spittles object is )", LocalDateTime.now(), spittles);
			spittles = new ArrayList<>(1);
			spittles.add(new Spittle("1 element of spittle auto-created"));
		}
		return spittles;
		
	}

	@Override
	public Spittle findSpittleById(long id) {
		logger.info("{} findSpittleById() called with id={}", LocalDateTime.now(), id);
		Spittle sp = null;
		if(id > 0){
			sp = new Spittle();
			Spittle.setId(id);
			sp.setMessage("Spittle for single spittle view");
			logger.info("{} found a split with id={} and message={}", LocalDateTime.now(), id, sp.getMessage());
		}else{
			sp = new Spittle();
			Spittle.setId(0L);
			sp.setMessage("Default Spittle for single spittle view");
		}
		return sp;
	}
	
	private int rands(Long max ,int min){
		return ThreadLocalRandom.current() .nextInt(4, 77);
		
	}

}
