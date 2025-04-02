package com.petpacket.final_project.dto.request.payment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpgradeTransactionRequest {
	private Long amount;
	private String bankCode;
	private String status;
	private String transactionNumber;
	private String invoiceNumber;
}
