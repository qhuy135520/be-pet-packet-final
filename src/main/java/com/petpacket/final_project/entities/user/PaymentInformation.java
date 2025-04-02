package com.petpacket.final_project.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "\"PaymentInformation\"", schema = "public")
public class PaymentInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_information_id")
	private Integer paymentInformationId;

	@Column(name = "bank_name")
	private String bankName;

	@Column(name = "account_number")
	private String accountNumber;
	
	@Column(name = "account_name")
	private String accountName;

	@Column(name = "release_date")
	private String releaseDate;
	
	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore
	private User user;

}
