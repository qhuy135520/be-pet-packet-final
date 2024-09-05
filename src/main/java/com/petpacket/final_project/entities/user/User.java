package com.petpacket.final_project.entities.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.petpacket.final_project.entities.Booking;
import com.petpacket.final_project.entities.Comment;
import com.petpacket.final_project.entities.Review;
import com.petpacket.final_project.entities.pet.Pet;
import com.petpacket.final_project.entities.service.Service;

@Entity
@Table(name = "\"User\"", schema = "public")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(nullable = false, name = "user_name")
	private String username;

	@Column(nullable = false, name = "password")
	private String password;

	@Column(nullable = false, name = "full_name")
	private String name;

	@Column(nullable = false, name = "gender")
	private Integer gender;

	@Column(unique = true, name = "email")
	private String email;

	@Column(name = "user_picture")
	private String userPicture;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "status")
	private Integer status;
	
	@Column(name = "login_provider")
	private String loginProvider;

	@OneToMany(mappedBy = "user")
	private List<ExternalLogin> externalLogins;

	@OneToMany(mappedBy = "user")
	private List<Pet> pets;

	@OneToMany(mappedBy = "user")
	private List<Service> services;

	@OneToMany(mappedBy = "user")
	private List<Review> reviews;

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	@OneToMany(mappedBy = "user")
	private List<Booking> bookings;

	public User() {
	}

	public User(Integer userId, String username, String password, String name, Integer gender, String email,
			String userPicture, Role role, String address, String phone, Integer status, String loginProvider,
			List<ExternalLogin> externalLogins, List<Pet> pets, List<Service> services, List<Review> reviews,
			List<Comment> comments, List<Booking> bookings) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.userPicture = userPicture;
		this.role = role;
		this.address = address;
		this.phone = phone;
		this.status = status;
		this.loginProvider = loginProvider;
		this.externalLogins = externalLogins;
		this.pets = pets;
		this.services = services;
		this.reviews = reviews;
		this.comments = comments;
		this.bookings = bookings;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLoginProvider() {
		return loginProvider;
	}

	public void setLoginProvider(String loginProvider) {
		this.loginProvider = loginProvider;
	}

	public List<ExternalLogin> getExternalLogins() {
		return externalLogins;
	}

	public void setExternalLogins(List<ExternalLogin> externalLogins) {
		this.externalLogins = externalLogins;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}


	
}
