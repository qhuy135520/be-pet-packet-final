package com.petpacket.final_project.services.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.entities.transaction.Transaction;
import com.petpacket.final_project.repository.transaction.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction getTransactionById(Integer transactionId) {
		return transactionRepository.findById(transactionId).get();
	}
	

}
