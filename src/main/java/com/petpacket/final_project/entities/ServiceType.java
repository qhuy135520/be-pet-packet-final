package com.petpacket.final_project.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"ServiceType\"", schema = "public")
public class ServiceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_type_id")
	private Integer serviceTypeId;

	@Column(nullable = false, name = "service_type_name")
	private String serviceTypeName;

	@Column(name = "description")
	private String description;
	
	@Column(name = "service_image")
	private String serviceImage;
	
	@Column(name = "status")
	private Integer status;

	@OneToMany(mappedBy = "serviceType")
	private List<Service> services;

	public ServiceType() {
	}

	public ServiceType(Integer serviceTypeId, String serviceTypeName, String description, String serviceImage,
			Integer status, List<Service> services) {
		super();
		this.serviceTypeId = serviceTypeId;
		this.serviceTypeName = serviceTypeName;
		this.description = description;
		this.serviceImage = serviceImage;
		this.status = status;
		this.services = services;
	}

	public Integer getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(Integer serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

}
