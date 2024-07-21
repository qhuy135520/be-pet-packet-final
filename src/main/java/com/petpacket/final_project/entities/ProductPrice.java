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
@Table(name = "\"ProductPrice\"", schema = "public")
public class ProductPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productPriceId;

	@ManyToOne
	@JoinColumn(name = "productId", nullable = false)
	private ServiceStore product;

	private Date startDate;
	private Date endDate;
	private Float numberPrice;

	public ProductPrice() {
	}

	public ProductPrice(Integer productPriceId, ServiceStore product, Date startDate, Date endDate, Float numberPrice) {
		super();
		this.productPriceId = productPriceId;
		this.product = product;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numberPrice = numberPrice;
	}

	public Integer getProductPriceId() {
		return productPriceId;
	}

	public void setProductPriceId(Integer productPriceId) {
		this.productPriceId = productPriceId;
	}

	public ServiceStore getProduct() {
		return product;
	}

	public void setProduct(ServiceStore product) {
		this.product = product;
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

	public Float getNumberPrice() {
		return numberPrice;
	}

	public void setNumberPrice(Float numberPrice) {
		this.numberPrice = numberPrice;
	}

}
