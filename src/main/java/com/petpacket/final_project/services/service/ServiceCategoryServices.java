package com.petpacket.final_project.services.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.entities.service.ServiceCategory;
import com.petpacket.final_project.repository.service.ServiceCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ServiceCategoryServices {
	@Autowired
	private ServiceCategoryRepository serviceCategoryRepository;

	
	public List<ServiceCategory> getAllServiceCategories() {
		return serviceCategoryRepository.findAllWithServices();
	}
}
