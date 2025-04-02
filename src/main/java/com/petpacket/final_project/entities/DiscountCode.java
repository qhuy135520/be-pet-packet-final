package com.petpacket.final_project.entities;

import java.time.LocalDateTime;

import org.springframework.boot.context.properties.bind.DefaultValue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "\"DiscountCode\"", schema = "public")
public class DiscountCode {
	@Id
	@Column(name = "discount_code_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer disCountCodeId;

	@Column(name = "code")
	private String code;

	@Column(name = "discount_value")
	private Integer discountValue;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "usage_limit")
	private Integer usageLimit;

	@Column(name = "usage_count", columnDefinition = "INTEGER DEFAULT 0")
	private Integer usageCount = 0;

	@Column(name = "is_active", columnDefinition = "BOOLEAN DEFAULT TRUE")
	private Boolean isActive = true;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;

}
