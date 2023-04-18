package edu.pnu.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {
	
	@Id
	private String id;
	private String password;
	private String name;
	private String role;
	
	public Member() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", password=" + password + ", name=" + name + ", role=" + role + "]";
	}
}