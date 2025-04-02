package com.petpacket.final_project.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.UpgradeRequest;
import com.petpacket.final_project.services.UpgradeRequestService;

@RestController
@RequestMapping("/api/upgrade-request")
public class UpgradeRequestController {

	@Autowired
	private UpgradeRequestService upgradeRequestService;

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUpgradeRequestByUserId(@PathVariable Integer userId) {
		UpgradeRequest upgradeRequest = upgradeRequestService.getUpgradeRequestByUserId(userId);
		SuccessResponse successResponse = new SuccessResponse();

		successResponse.setData(upgradeRequest);
		successResponse.setMessage("Fetch successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);

	}
}
