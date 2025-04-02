package com.petpacket.final_project.dto.response.revenue;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdminRevenueResponse {
	List<Double> revenueMonth;
	List<Double> revenueBookingMonth;
}
