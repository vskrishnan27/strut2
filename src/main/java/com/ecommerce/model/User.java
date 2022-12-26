package com.ecommerce.model;

public class User {
	private String username;
	private String password;
	private String UUID;
	private String role;
	private String phone;
	public User(String username, String password, String uUID, String role, String phone) {
		super();
		this.username = username;
		this.password = password;
		UUID = uUID;
		this.role = role;
		this.phone = phone;
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
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
