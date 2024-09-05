package com.petpacket.final_project.entities.service;

import com.petpacket.final_project.entities.product.ProductCategory;
import com.petpacket.final_project.entities.product.ProductStatus;

import jakarta.persistence.*;

@Entity
@Table(name = "\"ServiceStore\"", schema = "public")
public class ServiceStore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;

	@Column(nullable = false, name = "product_name")
	private String productName;

	@Column(name = "product_picture")
	private String productPicture;
	
	@Column(name = "description")
	private String description;

	@Column(name = "product_discount")
	private Float productDiscount;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory category;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private ProductStatus status;

	public ServiceStore() {
	}

	public ServiceStore(Integer productId, Service service, String productName, String productPicture, String description,
			Float productDiscount, ProductCategory category, ProductStatus status) {
		super();
		this.productId = productId;
		this.service = service;
		this.productName = productName;
		this.productPicture = productPicture;
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

	public String getProductPicture() {
		return productPicture;
	}

	public void setProductPicture(String productPicture) {
		this.productPicture = productPicture;
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
