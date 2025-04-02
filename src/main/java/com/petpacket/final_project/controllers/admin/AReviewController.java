package com.petpacket.final_project.controllers.admin;

import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.Review;
import com.petpacket.final_project.services.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/reviews")
public class AReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> getAllReviewByServiceId(){
        List<Review> list = reviewService.findAllReviews();
        SuccessResponse successReponse = new SuccessResponse();
        successReponse.setData(list);
        successReponse.setMessage("List Review");
        successReponse.setStatusCode(HttpStatus.OK.value());

        return ResponseEntity.ok(successReponse);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Integer reviewId) {
        try {
            reviewService.deleteReview(reviewId);
            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setMessage("Review deleted successfully");
            successResponse.setStatusCode(HttpStatus.OK.value());

            return ResponseEntity.ok(successResponse);
        } catch (RuntimeException e) {
            SuccessResponse successResponse = new SuccessResponse();
            successResponse.setMessage(e.getMessage());
            successResponse.setStatusCode(HttpStatus.NOT_FOUND.value());

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(successResponse);
        }
    }
}
