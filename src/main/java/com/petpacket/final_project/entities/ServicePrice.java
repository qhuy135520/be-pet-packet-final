package com.petpacket.final_project.entities;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"ServicePrice\"", schema = "public")
public class ServicePrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer servicePriceId;

	@ManyToOne
	@JoinColumn(name = "serviceId", nullable = false)
	private Service service;

	private Date startDate;
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "petTypeId")
	private PetType petType;

	private Float numberPrice;

	public ServicePrice() {
	}

	public ServicePrice(Integer servicePriceId, Service service, Date startDate, Date endDate, PetType petType,
			Float numberPrice) {
		super();
		this.servicePriceId = servicePriceId;
		this.service = service;
		this.startDate = startDate;
		this.endDate = endDate;
		this.petType = petType;
		this.numberPrice = numberPrice;
	}

	public Integer getServicePriceId() {
		return servicePriceId;
	}

	public void setServicePriceId(Integer servicePriceId) {
		this.servicePriceId = servicePriceId;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}

	public Float getNumberPrice() {
		return numberPrice;
	}

	public void setNumberPrice(Float numberPrice) {
		this.numberPrice = numberPrice;
	}

}
