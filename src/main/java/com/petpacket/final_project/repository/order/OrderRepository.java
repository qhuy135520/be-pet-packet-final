package com.petpacket.final_project.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}