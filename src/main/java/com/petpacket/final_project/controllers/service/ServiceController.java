package com.petpacket.final_project.controllers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.request.service.ServiceDTO;
import com.petpacket.final_project.dto.request.service.ServiceRequest;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.service.ServiceCategory;
import com.petpacket.final_project.repository.service.ServiceRepository;
import com.petpacket.final_project.services.service.SServices;
import com.petpacket.final_project.services.service.ServiceCategoryServices;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/api/services")
@Log4j2
public class ServiceController {

	@Autowired
	private SServices sServices;

	@Autowired
	private ServiceCategoryServices serviceCategoryServices;

	@GetMapping("/service-categories")
	public ResponseEntity<?> getAllServiceCategories() {
		List<ServiceCategory> list = serviceCategoryServices.getAllServiceCategories();

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(list);
		successResponse.setMessage("Service Categories");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

	@GetMapping("/")
	public ResponseEntity<?> getAllServices() {
		List<Service> listOfService = sServices.getAllServicesActive();

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listOfService);
		successResponse.setMessage("Services");

		return ResponseEntity.ok(successResponse);
	}

	@GetMapping("/{serviceId}")
	public ResponseEntity<?> getServiceById(@PathVariable Integer serviceId) {
		Service service = sServices.getServiceById(serviceId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(service);
		successResponse.setMessage("Services " + service.getServiceId());

		return ResponseEntity.ok(successResponse);

	}

	@GetMapping("/manage-service/{userId}")
	public ResponseEntity<?> getServiceByUserId(@PathVariable Integer userId) {
		List<Service> listServices = sServices.getServiceByUserId(userId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listServices);
		successResponse.setMessage("Fetch Successful");

		return ResponseEntity.ok(successResponse);

	}

	@PostMapping("/{userId}")
	public ResponseEntity<?> addService(@PathVariable Integer userId, @RequestBody ServiceRequest serviceRequest) {
		sServices.addService(serviceRequest, userId);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setMessage("Add Service successful");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

	@PutMapping("/{serviceId}")
	public ResponseEntity<?> updateService(@PathVariable Integer serviceId, @RequestBody ServiceDTO serviceDTO) {
		Service service = sServices.updateService(serviceId, serviceDTO);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(service);
		successResponse.setMessage("Update Service successful");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

	@PutMapping("/change-status/{serviceId}")
	public ResponseEntity<?> changeStatusService(@PathVariable Integer serviceId) {
		Service service = sServices.changeStatusService(serviceId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(service);
		successResponse.setMessage("Update Service successful");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

}