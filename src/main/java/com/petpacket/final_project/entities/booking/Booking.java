package com.petpacket.final_project.entities.booking;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.entities.Revenue;
import com.petpacket.final_project.entities.pet.Pet;
import com.petpacket.final_project.entities.pet.PetType;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.transaction.Transaction;
import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "\"Booking\"", schema = "public")
public class Booking {

	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;

	@CreationTimestamp
	@Column(name = "booking_date", nullable = false, updatable = false)
	private LocalDateTime bookingDate;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "note")
	private String note;

	@Column(name = "status")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "pet_type_id", nullable = false)
	private PetType petType;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Column(name = "session")
	private int session;
	
	@Column(name = "pet_num")
	private int petNum;

	@OneToOne(mappedBy = "booking")
	private Transaction transaction;

	@JsonIgnore
	@OneToMany(mappedBy = "booking")
	private List<Revenue> revenues;

}
