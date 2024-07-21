package com.petpacket.final_project.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.OrderDetail;
import com.petpacket.final_project.entities.OrderDetailId;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
