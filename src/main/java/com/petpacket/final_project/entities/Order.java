package com.petpacket.final_project.entities;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Order\"", schema = "public")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Order_iD")
	private Long orderId;

	@Column(name = "Order_date", nullable = false, columnDefinition = "timestamp default now()")
	private LocalDateTime orderDate;

	@Column(name = "Shipped_date")
	private LocalDateTime shippedDate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "total")
	private Long total;

	@Column(name = "transaction_type")
	private Short transactionType;

	@Column(name = "address_ship")
	private String addressShip;

	@Column(name = "status")
	private Short status;

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Service store;

	@OneToMany(mappedBy = "order")
	private Set<OrderDetail> orderDetail;

	public Order() {
	}

	public Order(Long orderId, LocalDateTime orderDate, LocalDateTime shippedDate, User user, Long total,
			Short transactionType, String addressShip, Short status, Service store, Set<OrderDetail> orderDetail) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.shippedDate = shippedDate;
		this.user = user;
		this.total = total;
		this.transactionType = transactionType;
		this.addressShip = addressShip;
		this.status = status;
		this.store = store;
		this.orderDetail = orderDetail;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDateTime getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDateTime shippedDate) {
		this.shippedDate = shippedDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Short getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(Short transactionType) {
		this.transactionType = transactionType;
	}

	public String getAddressShip() {
		return addressShip;
	}

	public void setAddressShip(String addressShip) {
		this.addressShip = addressShip;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Service getStore() {
		return store;
	}

	public void setStore(Service store) {
		this.store = store;
	}

	public Set<OrderDetail> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(Set<OrderDetail> orderDetail) {
		this.orderDetail = orderDetail;
	}

}
