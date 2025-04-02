package com.petpacket.final_project.entities.pet;

import java.time.LocalDate;

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
@Table(name = "\"Pet\"", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pet_id")
	private Integer petId;

	@Column(name = "pet_name", nullable = false)
	private String petName;

	@Column(name = "pet_birthdate")
	private LocalDate petBirthDate;

	@Column(name = "pet_gender")
	private String petGender;

	@ManyToOne
	@JoinColumn(name = "pet_type_id", nullable = false)
	private PetType petType;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User petOwner;

	@Column(name = "pet_weight")
	private Double petWeight;

	@Column(name = "description")
	private String Description;

	@Column(name = "pet_picture")
	private String petPicture;
}
