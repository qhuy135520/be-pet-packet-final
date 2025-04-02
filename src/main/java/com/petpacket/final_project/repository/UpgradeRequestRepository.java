package com.petpacket.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.UpgradeRequest;

@Repository
public interface UpgradeRequestRepository extends JpaRepository<UpgradeRequest, Integer> {
	
	@Query("SELECT ur FROM UpgradeRequest ur WHERE ur.user.userId = :userId")
	UpgradeRequest findUpgradeRequestByUserId(Integer userId);
}
