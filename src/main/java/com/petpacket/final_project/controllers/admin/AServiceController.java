package com.petpacket.final_project.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.services.service.SServices;

@RestController
@RequestMapping("/api/admin/services-management")
public class AServiceController {

	@Autowired
	private SServices sServices;

	@GetMapping("")
	public ResponseEntity<?> getAllServices() {
		List<Service> listServices = sServices.getAllServices();
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listServices);
		successResponse.setMessage("Fetch Successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

	@PutMapping("block-service/{serviceId}")
	public ResponseEntity<?> putMethodName(@PathVariable Integer serviceId) {
		Service service = sServices.blockService(serviceId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(service);
		successResponse.setMessage("Fetch Succesfully");

		return ResponseEntity.ok(successResponse);
	}

}
