package com.petpacket.final_project.entities.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "\"ServiceCategory\"", schema = "public")
public class ServiceCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_category_id")
	private Integer serviceCategoryId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "picture_txt")
	private String pictureTxt;
	
	@Column(name = "description")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "serviceCategory", fetch = FetchType.LAZY)
	private List<Service> services;
}
