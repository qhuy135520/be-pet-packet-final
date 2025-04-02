package com.petpacket.final_project.services.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.petpacket.final_project.dto.request.service.ServiceAddonDTO;
import com.petpacket.final_project.dto.request.service.ServiceDTO;
import com.petpacket.final_project.dto.request.service.ServiceDetailDTO;
import com.petpacket.final_project.dto.request.service.ServiceRequest;
import com.petpacket.final_project.entities.pet.PetType;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.service.ServiceAddon;
import com.petpacket.final_project.entities.service.ServiceCategory;
import com.petpacket.final_project.entities.service.ServiceDetail;
import com.petpacket.final_project.entities.service.ServicePicture;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.pet.PetTypeRepository;
import com.petpacket.final_project.repository.service.ServiceAddonRepository;
import com.petpacket.final_project.repository.service.ServiceCategoryRepository;
import com.petpacket.final_project.repository.service.ServiceDetailRepository;
import com.petpacket.final_project.repository.service.ServicePictureRepository;
import com.petpacket.final_project.repository.service.ServiceRepository;
import com.petpacket.final_project.repository.user.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@org.springframework.stereotype.Service
@Transactional
public class SServices {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PetTypeRepository petTypeRepository;

	@Autowired
	private ServicePictureRepository servicePictureRepository;

	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;

	@Autowired
	private ServiceDetailRepository serviceDetailRepository;

	@Autowired
	private ServiceAddonRepository serviceAddonRepository;

	public List<Service> getAllServices() {
		return serviceRepository.findAll();
	}

	public List<Service> getAllServicesActive() {
		return serviceRepository.findByStatus("active");
	}

	public Service getServiceById(Integer serviceId) {
		Service service = serviceRepository.findById(serviceId)
				.orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + serviceId));
		service.getServiceDetails().sort(Comparator.comparing((ServiceDetail s) -> s.getPetType().getPetTypeName())
				.thenComparing(ServiceDetail::getWeightMin));

		return service;
	}

	public List<Service> getServiceByUserId(Integer userId) {
		return serviceRepository.findServiceByUserId(userId);
	}

	public void addService(ServiceRequest serviceRequest, Integer userId) {
		User user = userRepository.findById(userId).get();
		ServiceCategory serviceCategory = serviceCategoryRepository
				.findById(serviceRequest.getService().getServiceCategoryId()).get();

		Service service = new Service();
		service.setCity(serviceRequest.getService().getCity());
		service.setAddress(serviceRequest.getService().getAddress());
		service.setCapacity(serviceRequest.getService().getCapacity());
		service.setCreateAt(LocalDateTime.now());
		service.setName(serviceRequest.getService().getName());
		service.setOverview(serviceRequest.getService().getOverview());
//		service.setServicePictures(listServicePicture);
		service.setUpdateAt(LocalDateTime.now());
		service.setUser(user);
//		service.setServiceDetails(listServiceDetail);
//		service.setServiceAddons(listServiceAddons);
		service.setServiceCategory(serviceCategory);
		service.setStatus("active");
		;
		serviceRepository.save(service);

		ServicePicture servicePicture = new ServicePicture();
		servicePicture.setPictureTxt(serviceRequest.getService().getPictureTxt());
		servicePicture.setService(service);
		servicePictureRepository.save(servicePicture);

		List<ServiceDetail> listServiceDetail = new ArrayList<>();
		List<ServiceDetailDTO> listServiceDetailDTO = serviceRequest.getServiceDetails();

		for (ServiceDetailDTO serviceDetailDTO : listServiceDetailDTO) {
			ServiceDetail serviceDetail = new ServiceDetail();
			serviceDetail.setCreatedAt(LocalDateTime.now());
			serviceDetail.setPrice(serviceDetailDTO.getPrice());
			serviceDetail.setTimeDuration(serviceDetailDTO.getDuration());
			serviceDetail.setTimeUnit(serviceDetailDTO.getUnit());
			serviceDetail.setUpdatedAt(LocalDateTime.now());
			serviceDetail.setWeightMax(serviceDetailDTO.getWeightMax());
			serviceDetail.setWeightMin(serviceDetailDTO.getWeightMin());
			serviceDetail.setService(service);
			PetType petType = petTypeRepository.findById(serviceDetailDTO.getPetType()).get();
			serviceDetail.setPetType(petType);
			listServiceDetail.add(serviceDetail);

		}

		serviceDetailRepository.saveAll(listServiceDetail);

		List<ServiceAddon> listServiceAddons = new ArrayList<>();
		List<ServiceAddonDTO> listAddonDTOs = serviceRequest.getServiceAddons();

		for (ServiceAddonDTO serviceAddonDTO : listAddonDTOs) {
			ServiceAddon serviceAddon = new ServiceAddon();
			serviceAddon.setCreatedAt(LocalDateTime.now());
			serviceAddon.setServiceAddonName(serviceAddonDTO.getServiceAddonName());
			serviceAddon.setServiceAddonPrice(serviceAddonDTO.getServiceAddonPrice());
			serviceAddon.setUpdatedAt(LocalDateTime.now());
			serviceAddon.setDescription(serviceAddonDTO.getDescription());
			serviceAddon.setService(service);
			listServiceAddons.add(serviceAddon);
		}

		serviceAddonRepository.saveAll(listServiceAddons);
	}

	public Service updateService(Integer serviceId, ServiceDTO serviceDTO) {
		Service service = serviceRepository.findById(serviceId).get();

		service.setName(serviceDTO.getName());
		service.setCity(serviceDTO.getCity());
		service.setAddress(serviceDTO.getAddress());
		service.setCapacity(serviceDTO.getCapacity());
		service.setOverview(serviceDTO.getOverview());

		return serviceRepository.save(service);

	}

	public Service changeStatusService(Integer serviceId) {
		Service service = serviceRepository.findById(serviceId).get();
		if (service.getStatus().equals("active")) {
			service.setStatus("inactive");
		} else {
			service.setStatus("active");
		}

		return serviceRepository.save(service);
	}

	public Service blockService(Integer serviceId) {
		Service service = serviceRepository.findById(serviceId).get();
		if (service.getStatus().equals("blocked")) {
			service.setStatus("active");
		} else {
			service.setStatus("blocked");
		}
		return serviceRepository.save(service);
	}
}
