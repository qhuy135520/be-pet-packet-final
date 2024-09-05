package com.petpacket.final_project.entities.service;

import jakarta.persistence.*;
import java.util.List;

import com.petpacket.final_project.entities.Booking;
import com.petpacket.final_project.entities.Comment;
import com.petpacket.final_project.entities.Review;
import com.petpacket.final_project.entities.pet.PetType;
import com.petpacket.final_project.entities.user.User;

@Entity
@Table(name = "\"Service\"", schema = "public")
public class Service {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_id")
	private Integer serviceId;

	@Column(nullable = false, name = "service_name")
	private String serviceName;

	@ManyToOne
	@JoinColumn(name = "service_type_id")
	private ServiceType serviceType;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Column(name = "address")
	private String address;
	
	@Column(name = "status")
	private Integer status;

	@OneToMany(mappedBy = "service")
	private List<ServicePicture> servicePictures;

	@OneToMany(mappedBy = "service")
	private List<ServicePrice> servicePrices;

	@OneToMany(mappedBy = "service")
	private List<ServiceStore> serviceStores;

	@OneToMany(mappedBy = "service")
	private List<Review> reviews;

	@OneToMany(mappedBy = "service")
	private List<Comment> comments;

	@OneToMany(mappedBy = "service")
	private List<Booking> bookings;

	@ManyToMany
	@JoinTable(name = "\"ServicePetType\"", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "pet_type_id"))
	private List<PetType> petTypes;

	public Service() {
	}

	public Service(Integer serviceId, String serviceName, ServiceType serviceType, String description, User user,
			String address, Integer status, List<ServicePicture> servicePictures, List<ServicePrice> servicePrices,
			List<ServiceStore> serviceStores, List<Review> reviews, List<Comment> comments, List<Booking> bookings,
			List<PetType> petTypes) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.serviceType = serviceType;
		this.description = description;
		this.user = user;
		this.address = address;
		this.status = status;
		this.servicePictures = servicePictures;
		this.servicePrices = servicePrices;
		this.serviceStores = serviceStores;
		this.reviews = reviews;
		this.comments = comments;
		this.bookings = bookings;
		this.petTypes = petTypes;
	}

	public Integer getServiceId() {
		return serviceId;
	}

	public void setServiceId(Integer serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<ServicePicture> getServicePictures() {
		return servicePictures;
	}

	public void setServicePictures(List<ServicePicture> servicePictures) {
		this.servicePictures = servicePictures;
	}

	public List<ServicePrice> getServicePrices() {
		return servicePrices;
	}

	public void setServicePrices(List<ServicePrice> servicePrices) {
		this.servicePrices = servicePrices;
	}

	public List<ServiceStore> getServiceStores() {
		return serviceStores;
	}

	public void setServiceStores(List<ServiceStore> serviceStores) {
		this.serviceStores = serviceStores;
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

	public List<PetType> getPetTypes() {
		return petTypes;
	}

	public void setPetTypes(List<PetType> petTypes) {
		this.petTypes = petTypes;
	}

}
