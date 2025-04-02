package com.petpacket.final_project.dto.response.revenue;

import com.petpacket.final_project.entities.user.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RevenueResponse {
	private User user;
	private double amount;
	private double totalAmount;
}
