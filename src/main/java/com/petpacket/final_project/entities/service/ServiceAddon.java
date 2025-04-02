package com.petpacket.final_project.entities.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.Enum.ECity;
import com.petpacket.final_project.entities.Review;
import com.petpacket.final_project.entities.booking.Booking;
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
@Table(name = "\"ServiceAddon\"", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceAddon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_addon_id")
	private Integer serviceAddonId;

	@Column(name = "service_addon_name")
	private String serviceAddonName;

	@Column(name = "service_addon_price")
	private double serviceAddonPrice;

	@Column(name = "description")
	private String description;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "service_id")
	private Service service;

}
