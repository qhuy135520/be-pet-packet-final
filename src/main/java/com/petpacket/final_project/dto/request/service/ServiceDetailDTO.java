package com.petpacket.final_project.dto.request.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDetailDTO {
	private double duration;
	private String unit;
	private Integer weightMax;
	private Integer weightMin;
	private double price;
	private Integer petType;
}
