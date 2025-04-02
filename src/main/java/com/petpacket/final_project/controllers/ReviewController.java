package com.petpacket.final_project.controllers;

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

import com.petpacket.final_project.dto.request.review.ReviewDTO;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.Review;
import com.petpacket.final_project.services.review.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping("/add-review")
	public ResponseEntity<?> addReview(@RequestBody ReviewDTO reviewDTO ){
		Review review = reviewService.addReview(reviewDTO);
		
		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setStatusCode(HttpStatus.OK.value());
		successResponse.setData(review);
		return ResponseEntity.ok(successResponse);
		
	}
	
	
	@GetMapping("/{serviceId}")
	public ResponseEntity<?> getAllReviewByServiceId(@PathVariable Integer serviceId){
		List<Review> list = reviewService.findAllReviewsByServiceId(serviceId);
		
		SuccessResponse successReponse = new SuccessResponse();
		successReponse.setData(list);
		successReponse.setMessage("List Review");
		successReponse.setStatusCode(HttpStatus.OK.value());
		
		return ResponseEntity.ok(successReponse);
		
		
	}
}
