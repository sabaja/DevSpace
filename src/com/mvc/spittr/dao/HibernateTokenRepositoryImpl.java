package com.mvc.spittr.dao;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.spittr.entity.PersistentLogin;

/**
 * http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
 * 
 * @author sabaja Moreover, since we will also provide RememberMe functionality,
 *         keeping track of token-data in database, we configured a
 *         PersistentTokenRepository implementation.
 * 
 *         Spring Security comes with two implementation of
 *         PersistentTokenRepository : JdbcTokenRepositoryImpl and
 *         InMemoryTokenRepositoryImpl. We could have opted for
 *         JdbcTokenRepositoryImpl [this post demonstrates the RememberMe with
 *         JdbcTokenRepositoryImpl], but since we are using Hibernate in our
 *         application, why not create a custom implementation using Hibernate
 *         instead of using JDBC? Shown below is an attempt for the same.
 */
@Repository("tokenRepositoryDAOImpl")
@Transactional
public class HibernateTokenRepositoryImpl extends AbstractDao<String, PersistentLogin>
		implements PersistentTokenRepository {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		logger.info("Creating Token for user : {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setLast_used(LocalDate.now(ZoneId.systemDefault()));
		this.persist(persistentLogin);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		logger.info("Fetch Token if any for seriesId : {}", seriesId);
		try {
			Criteria criteria = createEntityCriteria();
			criteria.add(Restrictions.eq("series", seriesId));
			PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
			// PersistentRememberMeToken(String username, String series, String
			// tokenValue, Date date)
			LocalDate date = persistentLogin.getLast_used();

			return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
					persistentLogin.getToken(), Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
		} catch (Exception e) {
			logger.info("Token {} not found", seriesId);
		}
		return null;
	}

	@Override
	public void removeUserTokens(String username) {
		logger.info("Removing Token if any for seriesId : {}", username);
		Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.eq("username", username));
		PersistentLogin persistentLogin = (PersistentLogin) criteria.uniqueResult();
		if(persistentLogin != null){
			logger.info("token {} of username {} deleted", persistentLogin.getToken(),persistentLogin.getUsername());
			this.delete(persistentLogin);
		}else{
			logger.info("username {} doesn't exist", username);
		}
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		logger.info("Updating Token for seriesId : {}", series);
		PersistentLogin persistentLogin = this.getByKey(tokenValue);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLast_used(lastUsed.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		update(persistentLogin);

	}

}
