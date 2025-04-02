package com.petpacket.final_project.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.petpacket.final_project.entities.transaction.TransactionDetail;

@Repository
public interface TransactionDetailRepository extends JpaRepository<TransactionDetail, Integer> {

}
