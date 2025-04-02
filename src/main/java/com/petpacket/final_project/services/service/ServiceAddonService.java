package com.petpacket.final_project.services.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.dto.request.service.ServiceAddonDTO;
import com.petpacket.final_project.entities.service.ServiceAddon;
import com.petpacket.final_project.repository.service.ServiceAddonRepository;
import com.petpacket.final_project.repository.service.ServiceRepository;

@Service
public class ServiceAddonService {
	@Autowired
	private ServiceAddonRepository serviceAddonRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	public void addServiceAddon(Integer serviceId, ServiceAddonDTO serviceAddonDTO) {
		com.petpacket.final_project.entities.service.Service service = serviceRepository.findById(serviceId).get();
		ServiceAddon serviceAddon = new ServiceAddon();
		serviceAddon.setService(service);
		serviceAddon.setServiceAddonName(serviceAddonDTO.getServiceAddonName());
		serviceAddon.setServiceAddonPrice(serviceAddonDTO.getServiceAddonPrice());
		serviceAddon.setDescription(serviceAddonDTO.getDescription());

		serviceAddonRepository.save(serviceAddon);

		service.setUpdateAt(LocalDateTime.now());
		serviceRepository.save(service);
	}

	public void deleteServiceAddon(Integer serviceAddonId) {
		serviceAddonRepository.deleteById(serviceAddonId);
	}

}
