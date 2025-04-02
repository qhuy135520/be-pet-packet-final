package com.petpacket.final_project.services.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.dto.request.service.ServiceDetailDTO;
import com.petpacket.final_project.entities.pet.PetType;
import com.petpacket.final_project.entities.service.ServiceDetail;
import com.petpacket.final_project.repository.pet.PetTypeRepository;
import com.petpacket.final_project.repository.service.ServiceDetailRepository;
import com.petpacket.final_project.repository.service.ServiceRepository;

@Service
public class ServiceDetailService {

	@Autowired
	private ServiceDetailRepository serviceDetailRepository;

	@Autowired
	private ServiceRepository serviceRepository;
	
	@Autowired
	private PetTypeRepository petTypeRepository;

	@Autowired
	private SServices sServices;

	public void addServiceDetail(ServiceDetailDTO serviceDetailDTO, Integer serviceId) {
		com.petpacket.final_project.entities.service.Service service = sServices.getServiceById(serviceId);

		ServiceDetail serviceDetail = new ServiceDetail();
		serviceDetail.setCreatedAt(LocalDateTime.now());
		serviceDetail.setPrice(serviceDetailDTO.getPrice());
		serviceDetail.setService(service);
		serviceDetail.setTimeDuration(serviceDetailDTO.getDuration());
		serviceDetail.setTimeUnit(serviceDetailDTO.getUnit());
		serviceDetail.setUpdatedAt(LocalDateTime.now());
		serviceDetail.setWeightMax(serviceDetailDTO.getWeightMax());
		serviceDetail.setWeightMin(serviceDetailDTO.getWeightMin());
		PetType petType = petTypeRepository.findById(serviceDetailDTO.getPetType()).get();
		serviceDetail.setPetType(petType);
		serviceDetailRepository.save(serviceDetail);

		service.setUpdateAt(LocalDateTime.now());
		serviceRepository.save(service);

	}
	
	public void deleteServiceDetail(Integer serviceDetailId) {
		serviceDetailRepository.deleteById(serviceDetailId);
	}

}
