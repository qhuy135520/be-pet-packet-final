package com.petpacket.final_project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "\"ServiceImage\"", schema = "public")
public class ServiceImage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer serviceImageId;

	@ManyToOne
	@JoinColumn(name = "serviceId", nullable = false)
	private Service service;

	private String imageTxt;

	public ServiceImage() {
	}

	public ServiceImage(Integer serviceImageId, Service service, String imageTxt) {
		super();
		this.serviceImageId = serviceImageId;
		this.service = service;
		this.imageTxt = imageTxt;
	}

	public Integer getServiceImageId() {
		return serviceImageId;
	}

	public void setServiceImageId(Integer serviceImageId) {
		this.serviceImageId = serviceImageId;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getImageTxt() {
		return imageTxt;
	}

	public void setImageTxt(String imageTxt) {
		this.imageTxt = imageTxt;
	}

}
