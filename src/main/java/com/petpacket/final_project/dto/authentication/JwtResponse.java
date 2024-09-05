package com.petpacket.final_project.dto.authentication;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtResponse {
	private String access_token;
	private String type = "Bearer";
	private UserResponse user;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public UserResponse getUser() {
		return user;
	}

	public void setUser(UserResponse user) {
		this.user = user;
	}

}