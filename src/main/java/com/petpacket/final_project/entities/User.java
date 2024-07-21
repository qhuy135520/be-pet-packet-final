package com.petpacket.final_project.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "\"User\"", schema = "public")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(nullable = false)
	private String userName;

	@Column(nullable = false)
	private String password;

	private String fullName;
	private Integer gender;

	@Column(unique = true)
	private String email;

	private String userImage;
	private Integer roleId;
	private String address;
	private String phone;
	private Integer status;
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

	public User(Integer userId, String userName, String password, String fullName, Integer gender, String email,
			String userImage, Integer roleId, String address, String phone, Integer status, String loginProvider,
			List<ExternalLogin> externalLogins, List<Pet> pets, List<Service> services, List<Review> reviews,
			List<Comment> comments, List<Booking> bookings) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.gender = gender;
		this.email = email;
		this.userImage = userImage;
		this.roleId = roleId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
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

	public String getUserImage() {
		return userImage;
	}

	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
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
