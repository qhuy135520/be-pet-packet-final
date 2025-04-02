package com.petpacket.final_project.dto.request.authentication;

import com.petpacket.final_project.Enum.EGender;
import com.petpacket.final_project.Enum.ECity;
import com.petpacket.final_project.Enum.EStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private Integer userId;
	private String username;
	private String email;
	private ECity address;
	private String name;
	private EGender gender;
	private String phone;
	private EStatus status;
	private String role;
	private String picture;
}
