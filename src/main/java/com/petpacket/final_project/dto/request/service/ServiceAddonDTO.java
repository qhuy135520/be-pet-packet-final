package com.petpacket.final_project.dto.request.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAddonDTO {
	private String serviceAddonName;
	private double serviceAddonPrice;
	private String description;

}
