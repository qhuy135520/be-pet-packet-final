package com.petpacket.final_project.entities.service;

import java.sql.Date;

import com.petpacket.final_project.entities.pet.PetType;

import jakarta.persistence.Column;
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
	@Column(name = "service_price_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer servicePriceId;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;

	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "pet_type_id")
	private PetType petType;

	@Column(name = "number_price")
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
