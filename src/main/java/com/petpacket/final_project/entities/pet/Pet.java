package com.petpacket.final_project.entities.pet;

import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.*;

@Entity
@Table(name = "\"Pet\"", schema = "public")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pet_id")
    private Integer petId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false,name = "pet_name")
    private String petName;

    @ManyToOne
    @JoinColumn(name = "pet_type_id", nullable = false)
    private PetType petType;

    @Column(name = "gender")
    private Integer gender;
    
    @Column(name = "pet_image")
    private String petImage;
    
    @Column(name = "status")
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
