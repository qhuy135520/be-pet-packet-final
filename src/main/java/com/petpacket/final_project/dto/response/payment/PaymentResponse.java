package com.petpacket.final_project.dto.response.payment;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7704145708966099379L;
	private Integer statusCode;
	private String message;
	private Object data;
}
