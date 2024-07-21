package com.petpacket.final_project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "\"ServiceStore\"", schema = "public")
public class ServiceStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;

	@ManyToOne
	@JoinColumn(name = "serviceId", nullable = false)
	private Service service;

	@Column(nullable = false)
	private String productName;

	private String productImage;
	private String description;
	private Float productDiscount;

	@ManyToOne
	@JoinColumn(name = "categoryId")
	private ProductCategory category;

	@ManyToOne
	@JoinColumn(name = "statusId")
	private ProductStatus status;

	public ServiceStore() {
	}

	public ServiceStore(Integer productId, Service service, String productName, String productImage, String description,
			Float productDiscount, ProductCategory category, ProductStatus status) {
		super();
		this.productId = productId;
		this.service = service;
		this.productName = productName;
		this.productImage = productImage;
		this.description = description;
		this.productDiscount = productDiscount;
		this.category = category;
		this.status = status;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getProductDiscount() {
		return productDiscount;
	}

	public void setProductDiscount(Float productDiscount) {
		this.productDiscount = productDiscount;
	}

	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public ProductStatus getStatus() {
		return status;
	}

	public void setStatus(ProductStatus status) {
		this.status = status;
	}

}
