package com.petpacket.final_project.entities.service;

import jakarta.persistence.*;

@Entity
@Table(name = "\"ServicePicture\"", schema = "public")
public class ServicePicture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_picture_id")
	private Integer servicePictureId;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;

	@Column(name = "picture_txt")
	private String pictureTxt;

	public ServicePicture() {
	}

	public ServicePicture(Integer servicePictureId, Service service, String pictureTxt) {
		super();
		this.servicePictureId = servicePictureId;
		this.service = service;
		this.pictureTxt = pictureTxt;
	}

	public Integer getServicePictureId() {
		return servicePictureId;
	}

	public void setServicePictureId(Integer servicePictureId) {
		this.servicePictureId = servicePictureId;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getPictureTxt() {
		return pictureTxt;
	}

	public void setPictureTxt(String pictureTxt) {
		this.pictureTxt = pictureTxt;
	}

}
