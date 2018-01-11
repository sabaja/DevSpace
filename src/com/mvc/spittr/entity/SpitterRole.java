package com.mvc.spittr.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "spitterRole")
@Table(name = "SPITTER_ROLE", schema = "HIBERNATE")
public class SpitterRole implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8166490859834508985L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID")
	private Integer roleID;

	// USER is the default value
	// http://tomee.apache.org/examples-trunk/jpa-enumerated/
	@Column(name = "ROLE_TYPE")
	@Enumerated(EnumType.STRING)
	private RoleType role = RoleType.USER;

	// Relationship bidirectional: roles is the name of Set<SpitterRole> variable
//	@ManyToMany(mappedBy = "roles")
//	private Set<Spitter> spitters = new HashSet<>();

	public SpitterRole(RoleType role) {
		super();
		this.role = role;
	}

	public SpitterRole(RoleType role, Set<Spitter> spitters) {
		super();
		this.role = role;
//		this.spitters = spitters;
	}

	public SpitterRole() {
		super();
	}

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

//	public Set<Spitter> getSpitters() {
//		return spitters;
//	}
//
//	public void setSpitters(Set<Spitter> spitters) {
//		this.spitters = spitters;
//	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
//		result = prime * result + ((spitters == null) ? 0 : spitters.hashCode());
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
		SpitterRole other = (SpitterRole) obj;
		if (role != other.role)
			return false;
		if (roleID == null) {
			if (other.roleID != null)
				return false;
		} else if (!roleID.equals(other.roleID))
			return false;
//		if (spitters == null) {
//			if (other.spitters != null)
//				return false;
//		} else if (!spitters.equals(other.spitters))
//			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SpitterRole [roleID=" + roleID + ", role=" + role + "]";
	}

}
