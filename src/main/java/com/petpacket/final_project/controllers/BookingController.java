package com.petpacket.final_project.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.request.booking.BookingRequest;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.booking.Booking;
import com.petpacket.final_project.entities.transaction.Transaction;
import com.petpacket.final_project.services.booking.BookingService;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("")
	public ResponseEntity<?> addBooking(@RequestBody BookingRequest bookingRequest) {
		Transaction transaction = bookingService.AddBooking(bookingRequest);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(transaction);
		successResponse.setMessage(
				"You have successfully booked a pet grooming appointment for your pet. Please check your listing above!");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

	@GetMapping("/{serviceId}")
	public ResponseEntity<?> getBookingByServiceId(@PathVariable Integer serviceId) {
		List<Booking> listBookings = bookingService.getBookingByServiceId(serviceId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setStatusCode(HttpStatus.OK.value());
		successResponse.setMessage("Fetch Successful");
		successResponse.setData(listBookings);

		return ResponseEntity.ok(successResponse);

	}

	@GetMapping("/history-Booking/{userId}")
	public ResponseEntity<?> getBookingByUserId(@PathVariable Integer userId) {
		List<Booking> listBookings = bookingService.getListBookingByUserId(userId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listBookings);
		successResponse.setMessage("Fetch successfully!");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

	@PostMapping("/available-booking/{serviceId}")
	public ResponseEntity<?> getBookingByDate(@PathVariable Integer serviceId, @RequestBody LocalDateTime startDate) {
		int petNumAvailable = bookingService.getBookingByStartDate(serviceId, startDate.toLocalDate());
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(petNumAvailable);
		successResponse.setMessage("Fetch successfull");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);

	}
	
	@GetMapping("/get-own-booking-pending/{userId}")
	public ResponseEntity<?> getBookingByDate(@PathVariable Integer userId) {
		List<Booking> listBookings = bookingService.getBookingOwnServicePending(userId);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listBookings);
		successResponse.setMessage("Fetch successfull");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);

	}

	@GetMapping("/get-booking-own-service/{userId}")
	public ResponseEntity<?> getBookingOwnService(@PathVariable Integer userId) {
		List<Booking> listBookings = bookingService.getBookingOwnService(userId);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listBookings);
		successResponse.setMessage("Fetch Successfully");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

	@PostMapping("/get-one-booking/{userId}")
	public ResponseEntity<?> getOneBooking(@PathVariable Integer userId, @RequestBody Integer serviceId) {
		Booking booking = bookingService.getOneBooking(userId, serviceId);
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(booking);
		successResponse.setMessage("Fetch Successfully");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok(successResponse);
	}

}
