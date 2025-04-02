package com.petpacket.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petpacket.final_project.entities.Revenue;
import com.petpacket.final_project.entities.user.User;

public interface RevenueRepository extends JpaRepository<Revenue, Integer> {
	List<Revenue> findByUser(User user);
	List<Revenue> findByStatus(String status);
	
}
