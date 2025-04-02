package com.petpacket.final_project.controllers.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.booking.Booking;
import com.petpacket.final_project.services.booking.BookingService;

@RestController
@RequestMapping("/api/admin/booking-management")
public class ABookingController {

	@Autowired
	private BookingService bookingService;

	@GetMapping
	public ResponseEntity<?> getAllBooking() {
		List<Booking> listBookings = bookingService.getAllBookingByStatus();
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listBookings);
		successResponse.setMessage("Fetch successfull!");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok(successResponse);
	}

}
