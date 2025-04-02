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
@Table(name = "\"TransactionDetail\"", schema = "public")
public class TransactionDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_detail_id")
	private Integer transactionDetailId;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "transaction_id", nullable = false)
	private Transaction transaction;

	@Column(name = "bank_code")
	private String bankCode;

	@Column(name = "invoice_number")
	private String invoiceNumber;

	@Column(name = "transaction_number")
	private String transactionNumber;

	@Column(name = "pay_date")
	private LocalDateTime payDate;

	@CreationTimestamp
	@Column(name = "transaction_date", nullable = false, updatable = false)
	private LocalDateTime transactionDate;
}
