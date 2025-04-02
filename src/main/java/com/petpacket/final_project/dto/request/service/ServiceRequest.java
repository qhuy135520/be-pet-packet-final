package com.petpacket.final_project.dto.request.service;

import java.util.List;
import com.petpacket.final_project.entities.service.ServiceAddon;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceRequest {
	private ServiceDTO service;
	private List<ServiceDetailDTO> serviceDetails;
	private List<ServiceAddonDTO> serviceAddons;

}
