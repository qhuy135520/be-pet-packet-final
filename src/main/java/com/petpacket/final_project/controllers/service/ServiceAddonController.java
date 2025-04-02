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

import com.petpacket.final_project.dto.request.service.ServiceAddonDTO;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.services.service.SServices;
import com.petpacket.final_project.services.service.ServiceAddonService;

@RestController
@RequestMapping("/api/service-addons")
public class ServiceAddonController {

	@Autowired
	private ServiceAddonService serviceAddonService;

	@Autowired
	private SServices sServices;

	@PostMapping("/{serviceId}")
	public ResponseEntity<?> addServiceAddon(@PathVariable Integer serviceId,
			@RequestBody ServiceAddonDTO serviceAddonDTO) {
		serviceAddonService.addServiceAddon(serviceId, serviceAddonDTO);
		Service service = sServices.getServiceById(serviceId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(service);
		successResponse.setMessage("Create Successful");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

	@DeleteMapping("/{serviceAddonId}")
	public ResponseEntity<?> deleteServiceAddon(@PathVariable Integer serviceAddonId, @RequestBody Integer serviceId) {
		serviceAddonService.deleteServiceAddon(serviceAddonId);
		Service service = sServices.getServiceById(serviceId);

		SuccessResponse successResponse = new SuccessResponse();

		successResponse.setData(service);
		successResponse.setMessage("Delete successfully");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

}
