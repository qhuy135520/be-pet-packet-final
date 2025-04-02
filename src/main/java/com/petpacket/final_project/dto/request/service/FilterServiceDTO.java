package com.petpacket.final_project.dto.request.service;

import com.petpacket.final_project.Enum.ECity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterServiceDTO {
	private Integer serviceCategoryId;
	private ECity city;
	private int radius;
	private int priceMin;
	private int priceMax;

}
