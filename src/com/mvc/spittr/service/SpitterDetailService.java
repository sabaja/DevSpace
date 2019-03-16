package com.mvc.spittr.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mvc.spittr.dao.SpitterDao;
import com.mvc.spittr.entity.Spitter;
import com.mvc.spittr.entity.SpitterRole;

/**
 * <p>
 * http://www.mkyong.com/spring-security/spring-security-hibernate-annotation-example/
 * </p>
 * 
 * @author sabaja
 *
 * {@link com.mvc.spittr.service.SpitterDetailService} class binds {@link com.mvc.spittr.entity.Spitter} user and {@link com.mvc.spittr.entity.SpitterRole}
 */
@Service("spitterDetailService")
public class SpitterDetailService implements UserDetailsService {

	@Autowired
	private SpitterDao spitterDao;

	/**
	 * @return {@link UserDetails} User(String username, String password,
	 *         Collection<? extends GrantedAuthority> authorities)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		Spitter spitter = this.spitterDao.findBySsoId(ssoId);
		if (null == spitter) {
			throw new UsernameNotFoundException("No user presents with sso_id: " + ssoId);
		}
		Set<GrantedAuthority> auths = this.buildUserAuthority(spitter.getRoles());
		return buildUser(spitter, auths);
	}

	// Converts my com.mkyong.users.model.User user to
	// org.springframework.security.core.userdetails.User
	private User buildUser(Spitter spitter, Set<GrantedAuthority> authorities) {
		return new User(spitter.getUsername(), spitter.getPassword(), authorities);
	}

	private Set<GrantedAuthority> buildUserAuthority(Set<SpitterRole> roles) {
		Set<GrantedAuthority> auths = new HashSet<>();

		for (SpitterRole role : roles) {
			auths.add(new SimpleGrantedAuthority(role.getRole().getRoleType()));
		}
		return auths;
	}
}
