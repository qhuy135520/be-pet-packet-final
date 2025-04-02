package com.petpacket.final_project.services.review;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.dto.request.review.ReviewDTO;
import com.petpacket.final_project.entities.Review;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.ReviewRepository;
import com.petpacket.final_project.services.service.SServices;
import com.petpacket.final_project.services.user.UserService;

import io.jsonwebtoken.io.IOException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private SServices sServices;

    @Autowired
    private UserService userService;

    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

	@Transactional(rollbackOn = {SQLException.class, IOException.class})
	public void deleteReview(Integer reviewId) {
		// Check if the review exists before attempting to delete it
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new RuntimeException("Review not found with id: " + reviewId));

		// Delete the review
		reviewRepository.delete(review);
	}


    public List<Review> findAllReviewsByServiceId(Integer serviceId) {
        return reviewRepository.findAllByService_ServiceId(serviceId);
    }

    @Transactional(rollbackOn = {SQLException.class, IOException.class})
    public Review addReview(ReviewDTO reviewDTO) {

        com.petpacket.final_project.entities.service.Service service = sServices
                .getServiceById(reviewDTO.getServiceId());

        User user = userService.getUserByUsername(reviewDTO.getUsername());

        Review review = new Review();
        review.setRating(reviewDTO.getRating());
        review.setReviewText(reviewDTO.getReviewTxt());
        review.setUser(user);
        review.setService(service);

        reviewRepository.save(review);

        return reviewRepository.save(review);
    }
}
