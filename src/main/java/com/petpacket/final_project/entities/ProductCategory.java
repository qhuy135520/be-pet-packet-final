package com.petpacket.final_project.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"ProductCategory\"", schema = "public")
public class ProductCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer categoryId;

	@Column(nullable = false)
	private String categoryName;

	@OneToMany(mappedBy = "category")
	private List<ServiceStore> serviceStores;

	public ProductCategory() {
	}

	public ProductCategory(Integer categoryId, String categoryName, List<ServiceStore> serviceStores) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.serviceStores = serviceStores;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<ServiceStore> getServiceStores() {
		return serviceStores;
	}

	public void setServiceStores(List<ServiceStore> serviceStores) {
		this.serviceStores = serviceStores;
	}

}
