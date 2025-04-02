package com.petpacket.final_project.entities.transaction;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.entities.booking.Booking;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"Transaction\"", schema = "public")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Integer transactionId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "booking_id", nullable = false)
	private Booking booking;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "amount_paid")
	private double amountPaid;

	@CreationTimestamp
	@Column(name = "created_date")
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@Column(name = "service_detail")
	private String serviceDetail;

	@Column(name = "size_pet")
	private String sizePet;

	@Column(name = "status")
	private String status;

	@OneToOne(mappedBy = "transaction")
	private TransactionDetail transactionDetail;

}
