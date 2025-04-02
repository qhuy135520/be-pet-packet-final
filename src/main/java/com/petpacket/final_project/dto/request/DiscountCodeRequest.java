package com.petpacket.final_project.dto.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCodeRequest {
	private String code;
	private Integer discountValue;
	private Integer minOrderValue;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer usageLimit;
	private Integer serviceId;
}
