package com.petpacket.final_project.entities.service;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.entities.pet.PetType;
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
@Table(name = "\"ServiceDetail\"", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_detail_id")
	private Integer serviceDetailId;

	@Column(name = "weight_min")
	private Integer weightMin;

	@Column(name = "weight_max")
	private Integer weightMax;

	@Column(name = "price")
	private double price;
	
	@Column(name = "time_unit")
	private String timeUnit;
	
	@ManyToOne
	@JoinColumn(name = "pet_type_id", nullable = false)
	private PetType petType;
	
	@Column(name = "time_duration")
	private double timeDuration;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

}
