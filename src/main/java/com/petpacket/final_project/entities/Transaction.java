package com.petpacket.final_project.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Transaction\"", schema = "public")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@ManyToOne
	@JoinColumn(name = "booking_id", nullable = false)
	private Booking booking;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "transaction_date")
	private Timestamp transactionDate;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "amount_paid")
	private Float amountPaid;

	public Transaction() {
	}

	public Transaction(Integer transactionId, Booking booking, Timestamp transactionDate, String paymentMethod,
			Float amountPaid) {
		super();
		this.transactionId = transactionId;
		this.booking = booking;
		this.transactionDate = transactionDate;
		this.paymentMethod = paymentMethod;
		this.amountPaid = amountPaid;
	}

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public Timestamp getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Timestamp transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Float getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Float amountPaid) {
		this.amountPaid = amountPaid;
	}
}
