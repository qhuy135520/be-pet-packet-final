package com.petpacket.final_project.controllers.transaction;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.request.transaction.TransactionDetailRequest;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.transaction.Transaction;
import com.petpacket.final_project.services.transaction.TransactionDetailService;
import com.petpacket.final_project.services.transaction.TransactionService;

@RestController
@RequestMapping("/api/transaction-detail")
public class TransactionDetailController {

	@Autowired
	private TransactionDetailService transactionDetailService;

	@Autowired
	private TransactionService transactionService;

	@PostMapping("")
	public ResponseEntity<?> createTransactionDetail(@RequestBody TransactionDetailRequest transactionDetailRequest) throws UnsupportedEncodingException {
		transactionDetailService.createTransactionDetail(transactionDetailRequest);
//		Transaction transaction = transactionService.getTransactionById(transactionDetailRequest.getTransactionId());

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setMessage("Created successfull!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

}
