package com.mvc.spittr.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "SPITTER", schema = "HIBERNATE")
public class Spitter implements Serializable {

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

//	@NotNull
	@Column(name="SSO_ID", unique=true, nullable=true)
	private String ssoId;
	
//	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "FIRSTNAME", nullable = true)
	private String firstName;

//	@NotNull
	@Size(min = 2, max = 30)
	@Column(name = "LASTNAME", nullable = true)
	private String lastName;

	@NotNull
	@Size(min = 1, max = 16)
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;

	@NotNull
	@Size(min = 5, max = 25)
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@NotNull
	@Column(name="EMAIL", nullable=false)
	private String email;

	// @OneToMany(cascade = CascadeType.ALL)
	// @Column(name = "SPITTER_ID", nullable = true)
	// @JoinTable(name = "SPITTER_SPITTLE", joinColumns = @JoinColumn(name =
	// "SPITTER_ID"), inverseJoinColumns = @JoinColumn(name = "SPITTLE_ID"))
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@Column(name = "SPITTER_ID", nullable = true)
	@JoinColumn(name = "SPITTER_ID")
	private List<Spittle> spittles = new LinkedList<>();

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "SPITTER_SPITTER_ROLE", joinColumns = { @JoinColumn(name = "SPITTER_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "ROLE_ID") })
	private Set<SpitterRole> roles = new HashSet<>();

	/**
	 * Default Constructor
	 */
	public Spitter() {
		super();

	}

	public Spitter(String firstName, String lastName, String username, String password, String email, List<Spittle> spittles,
			Set<SpitterRole> roles) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.spittles = spittles;
		this.roles = roles;
	}

	public Spitter(String firstName, String lastName, String username, String password, String spittles, String email,
			SpitterRole roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.spittles.add(new Spittle(spittles));
		this.roles.add(roles);

	}

	public Spitter(String firstName, String lastName, String username, String password, String ssoId,String spittles, String email,
			SpitterRole roles) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.ssoId = ssoId;
		this.email = email;
		this.spittles.add(new Spittle(spittles));
		this.roles.add(roles);

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

	public String getSsoId() {
		return ssoId;
	}

	public void setSsoId(String ssoId) {
		this.ssoId = ssoId;
	}

	public List<Spittle> getSpittles() {
		return spittles;
	}

	public void setSpittles(List<Spittle> spittles) {
		this.spittles = spittles;
	}

	public Set<SpitterRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<SpitterRole> roles) {
		this.roles = roles;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((roles == null) ? 0 : roles.hashCode());
		result = prime * result + ((spittles == null) ? 0 : spittles.hashCode());
		result = prime * result + ((ssoId == null) ? 0 : ssoId.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
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
		if (roles == null) {
			if (other.roles != null)
				return false;
		} else if (!roles.equals(other.roles))
			return false;
		if (spittles == null) {
			if (other.spittles != null)
				return false;
		} else if (!spittles.equals(other.spittles))
			return false;
		if (ssoId == null) {
			if (other.ssoId != null)
				return false;
		} else if (!ssoId.equals(other.ssoId))
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
		return "Spitter [id=" + id + ", ssoId=" + ssoId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", username=" + username + ", password=" + password + ", email=" + email + ", spittles=" + spittles
				+ ", roles=" + roles + "]";
	}


}
