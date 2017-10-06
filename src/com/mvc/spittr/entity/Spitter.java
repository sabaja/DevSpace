package com.mvc.spittr.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Table(name="SPITTER", schema="HIBERNATE")
public class Spitter implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5423001819232221965L;
	
	@Id
	@Column(name = "SPITTER_ID", nullable = false, unique = true)

	// GenerationType.AUTO
	// This is the default strategy and is portable across different databases.
	// Hibernate chooses the appropriate ID based on the database.
	// schema resource at runtime.
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "FIRSTNAME", nullable = true)
	private String firstName;
	
	@NotNull
	@Size(min = 2, max = 30)	
	@Column(name = "LASTNAME", nullable = true)
	private String lastName;
	
	@NotNull
	@Size(min=5, max=16)
	@Column(name = "USERNAME", nullable = true)
	private String username;
	
	@NotNull
	@Size(min=5, max=25)
	@Column(name = "PASSWORD", nullable = true)
	private String password;

//	@OneToMany(cascade = CascadeType.ALL)
//	@Column(name = "SPITTER_ID", nullable = true)
//	@JoinTable(name = "SPITTER_SPITTLE", joinColumns = @JoinColumn(name = "SPITTER_ID"), inverseJoinColumns = @JoinColumn(name = "SPITTLE_ID"))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Column(name = "SPITTER_ID", nullable = true)
	@JoinColumn(name = "SPITTER_ID")
	private List<Spittle> spittles = new ArrayList<>();
	
	/**
	 * Default Constructor
	 */
	public Spitter() {
		super();
		
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public List<Spittle> getSpittles() {
		return spittles;
	}

	public void setSpittles(List<Spittle> spittles) {
		this.spittles = spittles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((spittles == null) ? 0 : spittles.hashCode());
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
		Spitter other = (Spitter) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (spittles == null) {
			if (other.spittles != null)
				return false;
		} else if (!spittles.equals(other.spittles))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}



}
