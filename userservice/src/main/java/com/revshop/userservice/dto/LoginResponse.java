package com.revshop.userservice.dto;

public class LoginResponse {
	private int id;
	private String role;
	
	public int getId() {
		return id;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
}
