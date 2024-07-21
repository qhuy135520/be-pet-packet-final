package com.petpacket.final_project.entities;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "\"Review\"", schema = "public")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reviewId;

	@ManyToOne
	@JoinColumn(name = "serviceId", nullable = false)
	private Service service;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	private Integer rating;
	private String reviewText;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp reviewDate;

	public Review() {
	}

	public Review(Integer reviewId, Service service, User user, Integer rating, String reviewText,
			Timestamp reviewDate) {
		super();
		this.reviewId = reviewId;
		this.service = service;
		this.user = user;
		this.rating = rating;
		this.reviewText = reviewText;
		this.reviewDate = reviewDate;
	}

	public Integer getReviewId() {
		return reviewId;
	}

	public void setReviewId(Integer reviewId) {
		this.reviewId = reviewId;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Timestamp getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Timestamp reviewDate) {
		this.reviewDate = reviewDate;
	}
}
