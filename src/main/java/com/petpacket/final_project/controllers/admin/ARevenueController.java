package com.petpacket.final_project.controllers.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.dto.response.revenue.AdminRevenueResponse;
import com.petpacket.final_project.dto.response.revenue.RevenueResponse;
import com.petpacket.final_project.entities.Revenue;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.RevenueRepository;
import com.petpacket.final_project.services.RevenueService;
import com.petpacket.final_project.services.user.UserService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/admin/revenue-management")
public class ARevenueController {
	@Autowired
	private UserService userService;

	@Autowired
	private RevenueService revenueService;

	@GetMapping("")
	public ResponseEntity<?> getServiceProvider() {
		List<RevenueResponse> listRevenueResponses = userService.getUserByRoleProvider();
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listRevenueResponses);
		successResponse.setMessage("Fetch Successfully");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getRevenueByUserId(@PathVariable Integer userId) {
		List<Revenue> listRevenues = revenueService.getRevenueByUserId(userId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listRevenues);
		successResponse.setMessage("Fetch Successful!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}
	
	@GetMapping("/all-revenue/{year}")
	public ResponseEntity<?> getAllRevenue(@PathVariable Integer year){
		AdminRevenueResponse revenueMonth = revenueService.getAllRevenueAdmin(year);
		
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(revenueMonth);
		successResponse.setMessage("Fetch Successful!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

	@PostMapping("/set-paid/{userId}")
	public ResponseEntity<?> setPaidRevenue(@PathVariable Integer userId) throws MessagingException {
		revenueService.setPaidRevenue(userId);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setMessage("Fetch Successful!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}
}
