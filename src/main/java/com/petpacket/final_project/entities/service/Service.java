package com.petpacket.final_project.entities.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.Enum.ECity;
import com.petpacket.final_project.entities.DiscountCode;
import com.petpacket.final_project.entities.Review;
import com.petpacket.final_project.entities.booking.Booking;
import com.petpacket.final_project.entities.pet.PetType;
import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "\"Service\"", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Service {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private Integer serviceId;

	@Column(name = "city")
	@Enumerated(EnumType.STRING)
	private ECity city;

	@Column(name = "address")
	private String address;

	@Column(name = "overview")
	private String overview;
	
	@Column(name = "name")
	private String name;

	@Column(name = "capacity")
	private Integer capacity;
	
	@Column(name = "create_at")
	private LocalDateTime createAt;
	
	@Column(name = "update_at")
	private LocalDateTime updateAt;
	
	@Column(name = "status")
	private String status;

	@OneToMany(mappedBy = "service")
	private List<ServiceDetail> serviceDetails;
	
	@OneToMany(mappedBy = "service")
	private List<ServiceAddon> ServiceAddons;

	@JsonIgnore
	@OneToMany(mappedBy = "service")
	private List<Booking> bookings;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "service_category_id", nullable = false)
	private ServiceCategory serviceCategory;

	@OneToMany(mappedBy = "service")
	private List<ServicePicture> servicePictures;

	@OneToMany(mappedBy = "service")
	private List<Review> review;
	
	@JsonIgnore
	@OneToMany(mappedBy = "service")
	private List<DiscountCode> discountCodes;

}
