package com.petpacket.final_project.entities.pet;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.entities.booking.Booking;
import com.petpacket.final_project.entities.service.ServiceDetail;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "\"PetType\"", schema = "public")
public class PetType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_type_id")
	private Integer petTypeId;

	@Column(name = "pet_type_name", nullable = false)
	private String petTypeName;

	@JsonIgnore
	@OneToMany(mappedBy = "petType")
	private Set<Pet> pets;

	@JsonIgnore
	@OneToMany(mappedBy = "petType")
	private Set<ServiceDetail> serviceDetails;
	
	@JsonIgnore
	@OneToMany(mappedBy = "petType")
	private Set<Booking> bookings;

}
