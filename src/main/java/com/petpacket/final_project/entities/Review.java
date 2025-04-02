package com.petpacket.final_project.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"Review\"", schema = "public")
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "review_id")
	private Integer reviewId;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "rating")
	private Integer rating;

	@Column(name = "review_text")
	private String reviewText;

	@CreationTimestamp
	@Column(name = "review_date")
	private LocalDateTime reviewDate;

}
