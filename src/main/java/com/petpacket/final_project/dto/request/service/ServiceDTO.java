package com.petpacket.final_project.dto.request.service;

import com.petpacket.final_project.Enum.ECity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
	private String name;
	private ECity city;
	private String address;
	private Integer capacity;
	private String overview;
	private String pictureTxt;
	private Integer serviceCategoryId;
}
