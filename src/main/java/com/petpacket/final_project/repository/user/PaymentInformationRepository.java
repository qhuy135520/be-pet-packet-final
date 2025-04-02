package com.petpacket.final_project.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.user.PaymentInformation;

@Repository
public interface PaymentInformationRepository extends JpaRepository<PaymentInformation, Integer> {
	@Query("DELETE FROM PaymentInformation p WHERE p.user.id = :userId ")
	PaymentInformation deleteByUserId(@Param("userId") Integer userId);
}
