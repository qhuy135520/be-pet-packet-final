package com.petpacket.final_project.dto.request.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInRequest {
	private String username;
	private String password;

}