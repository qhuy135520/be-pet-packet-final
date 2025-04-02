package com.petpacket.final_project.entities.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.Enum.ECity;
import com.petpacket.final_project.Enum.EGender;
import com.petpacket.final_project.Enum.EStatus;
import com.petpacket.final_project.entities.Blog;
import com.petpacket.final_project.entities.Comment;
import com.petpacket.final_project.entities.Message;
import com.petpacket.final_project.entities.Revenue;
import com.petpacket.final_project.entities.Review;
import com.petpacket.final_project.entities.booking.Booking;
import com.petpacket.final_project.entities.pet.Pet;
import com.petpacket.final_project.entities.service.Service;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "\"User\"", schema = "public")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(nullable = false, name = "user_name")
	private String username;

	@JsonIgnore
	@Column(nullable = false, name = "password")
	private String password;

	@Column(nullable = false, name = "full_name")
	private String name;

	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private EGender gender;

	@Column(unique = true, name = "email")
	private String email;

	@Column(name = "user_picture")
	private String userPicture;

	@ManyToOne
	@JoinColumn(name = "role_id", nullable = false)
	private Role role;

	@Column(name = "city")
	@Enumerated(EnumType.STRING)
	private ECity city;

	@Column(name = "phone")
	private String phone;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private EStatus status;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@JsonIgnore
	@OneToMany(mappedBy = "petOwner")
	private Set<Pet> pets;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Review> reviews;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Service> services;

	@JsonIgnore
	@OneToMany(mappedBy = "sender")
	private List<Message> sentMessages;

	@JsonIgnore
	@OneToMany(mappedBy = "receiver")
	private List<Message> receiveMessages;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Revenue> revenues;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Blog> blogs;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private PaymentInformation paymentInformation;

}
