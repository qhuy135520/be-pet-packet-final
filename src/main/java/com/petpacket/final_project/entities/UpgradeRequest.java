package com.petpacket.final_project.entities;

import java.time.LocalDateTime;

import com.petpacket.final_project.entities.user.User;

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
@Table(name = "\"UpgradeRequest\"", schema = "public")
public class UpgradeRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "upgrade_request_id", nullable = false)
	private Integer upgradeRequestId;

	@OneToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "status", nullable = false)
	private String status;

	@Column(name = "request_date")
	private LocalDateTime requestDate;

	@Column(name = "approved_date")
	private LocalDateTime approvedDate;
	
	@Column(name = "picture_Txt")
	private String pictureTxt;

}
