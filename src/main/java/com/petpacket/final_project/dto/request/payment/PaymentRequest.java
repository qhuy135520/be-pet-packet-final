package com.petpacket.final_project.dto.request.payment;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2952917701651628386L;
	private long amount;
	private Integer userId;
	private String bankCode;

}
