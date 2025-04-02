package com.petpacket.final_project.controllers;

import java.time.LocalDateTime;
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

import com.petpacket.final_project.dto.request.DiscountCodeRequest;
import com.petpacket.final_project.dto.response.ErrorResponse;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.DiscountCode;
import com.petpacket.final_project.services.DiscountCodeService;

@RestController
@RequestMapping("/api/discount-code")
public class DiscountCodeController {
	@Autowired
	private DiscountCodeService discountCodeService;

	@GetMapping("/{serviceId}")
	public ResponseEntity<?> getDiscountCode(@PathVariable Integer serviceId) {
		List<DiscountCode> listDiscountCodes = discountCodeService.getDiscountCodeByServiceId(serviceId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listDiscountCodes);
		successResponse.setMessage("Fetch Data");
		successResponse.setStatusCode(HttpStatus.OK.value());

		return ResponseEntity.ok().body(successResponse);
	}

	@PostMapping("")
	public ResponseEntity<?> createVoucher(@RequestBody DiscountCodeRequest discountCodeRequest) {
		List<DiscountCode> listDiscountCodes = discountCodeService.createVoucher(discountCodeRequest);

		if (listDiscountCodes == null) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setError("Code Existed");
			errorResponse.setMessage("Please try again, code is already existed!");
			errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(errorResponse);
		} else {
			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setData(listDiscountCodes);
			successResponse.setMessage("Fetch Data");
			successResponse.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok().body(successResponse);
		}

	}

	@PutMapping("/{disCountCodeId}")
	public ResponseEntity<?> updateStatusVoucher(@PathVariable Integer disCountCodeId) {
		List<DiscountCode> listDiscountCodes = discountCodeService.updateStatusVoucher(disCountCodeId);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(listDiscountCodes);
		successResponse.setMessage("Fetch Data");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok().body(successResponse);
	}

	@GetMapping("/discount/{code}")
	public ResponseEntity<?> getDiscountByCode(@PathVariable String code) {
		DiscountCode discountCode = discountCodeService.getDiscountByCode(code);
		if (discountCode == null) {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setError("Code not Existed");
			errorResponse.setMessage("Please try again, code is not existed!");
			errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok().body(errorResponse);
		} else {
			if (discountCode.getEndDate().isBefore(LocalDateTime.now())
					|| discountCode.getStartDate().isAfter(LocalDateTime.now())) {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setError("Code is Expired");
				errorResponse.setMessage("Please try again, code is expired!");
				errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
				return ResponseEntity.ok().body(errorResponse);
			} else if (discountCode.getUsageCount() >= discountCode.getUsageLimit()) {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setError("Code is out of turn");
				errorResponse.setMessage("Please try again, code is out of turn!");
				errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
				return ResponseEntity.ok().body(errorResponse);
			} else if (!discountCode.getIsActive()) {
				ErrorResponse errorResponse = new ErrorResponse();
				errorResponse.setError("Code is inactive");
				errorResponse.setMessage("Please try again, code is inactive!");
				errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			}
		}
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData(discountCode.getDiscountValue());
		successResponse.setMessage("Fetch Data");
		successResponse.setStatusCode(HttpStatus.OK.value());
		return ResponseEntity.ok().body(successResponse);

	}

}
