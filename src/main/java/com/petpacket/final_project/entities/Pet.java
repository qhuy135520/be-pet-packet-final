package com.petpacket.final_project.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Pet\"", schema = "public")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer petId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(nullable = false)
    private String petName;

    @ManyToOne
    @JoinColumn(name = "petTypeId", nullable = false)
    private PetType petType;

    private Integer gender;
    private String petImage;
    private String status;

    public Pet() {
	}

	public Pet(Integer petId, User user, String petName, PetType petType, Integer gender, String petImage,
			String status) {
		super();
		this.petId = petId;
		this.user = user;
		this.petName = petName;
		this.petType = petType;
		this.gender = gender;
		this.petImage = petImage;
		this.status = status;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getPetImage() {
		return petImage;
	}

	public void setPetImage(String petImage) {
		this.petImage = petImage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

    
}
