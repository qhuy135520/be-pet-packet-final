package com.petpacket.final_project.repository.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.service.ServiceCategory;

@Repository
public interface ServiceCategoryRepository extends JpaRepository<ServiceCategory, Integer> {
	@Query("SELECT sc FROM ServiceCategory sc")
	List<ServiceCategory> findAllWithServices();
}
