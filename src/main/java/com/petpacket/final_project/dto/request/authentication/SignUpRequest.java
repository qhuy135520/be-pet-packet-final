package com.petpacket.final_project.dto.request.authentication;

import java.io.Serializable;

import com.petpacket.final_project.Enum.ECity;
import com.petpacket.final_project.Enum.EGender;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private String username;
	private String email;
	private String password;
	private ECity city;
	private String name;
	private EGender gender;
	private String phone;

}