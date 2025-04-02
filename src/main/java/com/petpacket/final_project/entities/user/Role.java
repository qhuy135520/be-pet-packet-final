package com.petpacket.final_project.entities.user;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.petpacket.final_project.Enum.ERole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter

@Table(name = "\"Role\"", schema = "public")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Integer roleId;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "role_name")
	private ERole roleName;

	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private Set<User> users = new HashSet<>();

	public Role() {
	}

	public Role(ERole name) {
		this.roleName = name;
	}
}
