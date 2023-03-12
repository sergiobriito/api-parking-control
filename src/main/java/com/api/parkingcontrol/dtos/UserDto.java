package com.api.parkingcontrol.dtos;

import java.util.List;

import com.api.parkingcontrol.enums.RoleName;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class UserDto {
	
	@NotBlank
	private String username;
	@NotBlank
	private String password;
	@NotEmpty
	private List<RoleName> roles;
	
	
	public List<RoleName> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleName> roles) {
		this.roles = roles;
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
	
	
}
