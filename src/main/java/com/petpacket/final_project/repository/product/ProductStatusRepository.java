package com.petpacket.final_project.repository.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.ProductStatus;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer> {
}
