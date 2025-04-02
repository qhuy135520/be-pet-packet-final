package com.petpacket.final_project.dto.request.transaction;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetailRequest {
	private double amount;
	private String bankCode;
	private String status;
	private String transactionNumber;
	private String invoiceNumber;
	private String transactionId;

}
