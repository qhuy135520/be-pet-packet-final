package com.petpacket.final_project.entities.product;

import jakarta.persistence.*;
import java.util.List;

import com.petpacket.final_project.entities.service.ServiceStore;

@Entity
@Table(name = "\"ProductStatus\"", schema = "public")
public class ProductStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "status_id")
	private Integer statusId;

	@Column(nullable = false, name = "status_name")
	private String statusName;

	@OneToMany(mappedBy = "status")
	private List<ServiceStore> serviceStores;

	public ProductStatus() {
	}

	public ProductStatus(Integer statusId, String statusName, List<ServiceStore> serviceStores) {
		super();
		this.statusId = statusId;
		this.statusName = statusName;
		this.serviceStores = serviceStores;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public List<ServiceStore> getServiceStores() {
		return serviceStores;
	}

	public void setServiceStores(List<ServiceStore> serviceStores) {
		this.serviceStores = serviceStores;
	}
}
