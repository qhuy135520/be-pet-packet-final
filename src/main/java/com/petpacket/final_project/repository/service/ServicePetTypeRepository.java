package com.petpacket.final_project.repository.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.ServicePetType;
import com.petpacket.final_project.entities.ServicePetTypeId;

@Repository
public interface ServicePetTypeRepository extends JpaRepository<ServicePetType, ServicePetTypeId > {
}
