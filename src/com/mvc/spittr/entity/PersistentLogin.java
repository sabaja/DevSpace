package com.mvc.spittr.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * http://websystique.com/springmvc/spring-mvc-4-and-spring-security-4-integration-example/
 */

@Entity
@Table(name = "PERSISTENT_LOGINS", schema = "HIBERNATE")
public class PersistentLogin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1466913776344583042L;

	@Id
	@Column(name = "SERIES")
	private String series;

	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;

	@Column(name = "TOKEN", nullable = false, unique = true)
	private String token;

	@Column(name = "LAST_USED")
	private LocalDate last_used;

	public PersistentLogin() {
		super();
	}

	public PersistentLogin(String series, String username, String token, LocalDate last_used) {
		super();
		this.series = series;
		this.username = username;
		this.token = token;
		this.last_used = last_used;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public LocalDate getLast_used() {
		return last_used;
	}

	public void setLast_used(LocalDate last_used) {
		this.last_used = last_used;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((last_used == null) ? 0 : last_used.hashCode());
		result = prime * result + ((series == null) ? 0 : series.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PersistentLogin other = (PersistentLogin) obj;
		if (last_used == null) {
			if (other.last_used != null)
				return false;
		} else if (!last_used.equals(other.last_used))
			return false;
		if (series == null) {
			if (other.series != null)
				return false;
		} else if (!series.equals(other.series))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PersistentLogin [series=" + series + ", username=" + username + ", token=" + token + ", last_used="
				+ last_used + "]";
	}

}