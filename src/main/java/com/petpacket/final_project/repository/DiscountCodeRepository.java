package com.petpacket.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.DiscountCode;
import com.petpacket.final_project.entities.service.Service;

@Repository
public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Integer> {
	List<DiscountCode> findByService(Service service);
	DiscountCode findByCode(String code);
}
