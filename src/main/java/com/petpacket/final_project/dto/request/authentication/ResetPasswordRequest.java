package com.petpacket.final_project.dto.request.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResetPasswordRequest {
	private String email;

	private String newPassword;

	private String confirmPassword;

}
