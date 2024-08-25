package com.petpacket.final_project.repository.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.service.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer> {
}
