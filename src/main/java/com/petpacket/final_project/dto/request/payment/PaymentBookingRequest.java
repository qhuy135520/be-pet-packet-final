package com.petpacket.final_project.dto.request.payment;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentBookingRequest {
	private List<Integer> listBookingIds;
	private double amountPaid;
}
