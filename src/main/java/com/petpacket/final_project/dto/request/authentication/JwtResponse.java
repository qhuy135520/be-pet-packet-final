package com.petpacket.final_project.dto.request.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
	private String access_token;
	private String type = "Bearer";
	private UserResponse user;
}