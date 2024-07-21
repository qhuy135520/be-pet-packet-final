package com.petpacket.final_project.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"PetType\"", schema = "public")
public class PetType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer petTypeId;

	@Column(nullable = false)
	private String petTypeName;

	private Integer weightFrom;
	private Integer weightTo;

	@OneToMany(mappedBy = "petType")
	private List<Pet> pets;

	@OneToMany(mappedBy = "petType")
	private List<ServicePrice> servicePrices;

	@ManyToMany(mappedBy = "petTypes")
	private List<Service> services;

	public PetType() {
	}

	public PetType(Integer petTypeId, String petTypeName, Integer weightFrom, Integer weightTo, List<Pet> pets,
			List<ServicePrice> servicePrices, List<Service> services) {
		super();
		this.petTypeId = petTypeId;
		this.petTypeName = petTypeName;
		this.weightFrom = weightFrom;
		this.weightTo = weightTo;
		this.pets = pets;
		this.servicePrices = servicePrices;
		this.services = services;
	}

	public Integer getPetTypeId() {
		return petTypeId;
	}

	public void setPetTypeId(Integer petTypeId) {
		this.petTypeId = petTypeId;
	}

	public String getPetTypeName() {
		return petTypeName;
	}

	public void setPetTypeName(String petTypeName) {
		this.petTypeName = petTypeName;
	}

	public Integer getWeightFrom() {
		return weightFrom;
	}

	public void setWeightFrom(Integer weightFrom) {
		this.weightFrom = weightFrom;
	}

	public Integer getWeightTo() {
		return weightTo;
	}

	public void setWeightTo(Integer weightTo) {
		this.weightTo = weightTo;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<ServicePrice> getServicePrices() {
		return servicePrices;
	}

	public void setServicePrices(List<ServicePrice> servicePrices) {
		this.servicePrices = servicePrices;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

}
