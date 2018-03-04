package com.mvc.spittr.entity;

import java.io.Serializable;

public enum RoleType implements Serializable {
	USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), DB("ROLE_DB");

	private String roleType;

	private RoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getRoleType() {
		return roleType;
	}

	public static RoleType getRoleById(int id) {
		RoleType[] types = RoleType.values();
		int len = types.length;
		if (id >= len) {
			id = len - 1;
		}
		if (id < 0) {
			id = 0;
		}
		return RoleType.values()[id];
	}
}
