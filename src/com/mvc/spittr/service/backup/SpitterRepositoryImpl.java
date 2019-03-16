package com.mvc.spittr.service.backup;

import java.lang.invoke.MethodHandles;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.mvc.spittr.entity.Spitter;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

	// This pattern can be cut and pasted across classes.
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private static Collection<Spitter> spitters = new HashSet<>();
	private final static SecureRandom random = new SecureRandom();
	private final static int SIZE = 2;

//	//@Autowired
//	private static Spitter spitter;
	static {
		
		logger.info("{} spitter size {})", LocalDateTime.now(), SIZE); 
			for(int i = 0; i < SIZE ; i++){
			Spitter spitter = new Spitter();
			spitter.setFirstName(randomString());
			spitter.setLastName(randomString());
			spitter.setPassword(randomString());
			spitters.add(spitter);
			logger.info("{} Fake Spitter created with(firstname= {},lastname = {}, password = {})", LocalDateTime.now(), 
					spitter.getFirstName(), spitter.getLastName(), spitter.getPassword());
			}
	
	}

	@Override
	public Spitter findByUserName(String username) {
		
		Spitter spitter = spitters.iterator().next();
		logger.info("spitter found {}", spitter);
		spitter.setUsername(username);
		return spitter;
	}

	@Override
	public void save(Spitter spitter) {
		logger.info("{} Spitter saved with(firstname= {},lastname = {}, user = {}, password = {})", LocalDateTime.now(), 
				spitter.getFirstName(), spitter.getLastName(), spitter.getPassword());
//		SpitterRepositoryImpl.spitter = spitter;
	}

//https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
	private static String randomString() {
		 return new BigInteger(130, random).toString(32);
	}

	public static void main(String[] args) {

		spitters.stream().forEach(s -> System.out.println(s));
	}
}
