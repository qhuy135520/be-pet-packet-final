package com.petpacket.final_project.controllers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.request.service.ServiceDetailDTO;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.services.service.SServices;
import com.petpacket.final_project.services.service.ServiceDetailService;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/service-detail")
@Log4j2
public class ServiceDetailController {
	@Autowired
	private ServiceDetailService serviceDetailService;

	@Autowired
	private SServices sServices;

	@PostMapping("/{serviceId}")
	public ResponseEntity<?> addServiceDetail(@PathVariable Integer serviceId,
			@RequestBody ServiceDetailDTO serviceDetailDTO) {
		serviceDetailService.addServiceDetail(serviceDetailDTO, serviceId);

		Service service = sServices.getServiceById(serviceId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(service);
		successResponse.setMessage("Add Successfull");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);

	}

	@DeleteMapping("/{serviceDetailId}")
	public ResponseEntity<?> deleteServiceDetail(@PathVariable Integer serviceDetailId,
			@RequestBody Integer serviceId) {
		serviceDetailService.deleteServiceDetail(serviceDetailId);
		Service service = sServices.getServiceById(serviceId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(service);
		successResponse.setMessage("Add Successfull");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);

	}

}
