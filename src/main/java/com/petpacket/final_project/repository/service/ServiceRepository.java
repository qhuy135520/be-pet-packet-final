package com.petpacket.final_project.repository.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.user.User;


@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer>, JpaSpecificationExecutor<Service> {
	@Query("SELECT s FROM Service s WHERE s.user.userId = :userId")
	List<Service> findServiceByUserId(Integer userId);
	
	List<Service> findByStatus(String status);
	
	List<Service> findByUser(User user);
}
