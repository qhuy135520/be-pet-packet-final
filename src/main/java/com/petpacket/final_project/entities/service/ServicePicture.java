package com.petpacket.final_project.entities.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "\"ServicePicture\"", schema = "public")
public class ServicePicture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_picture_id")
	private Integer servicePictureId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;
	
	@Column(name = "picture_txt")
	private String pictureTxt;
}
