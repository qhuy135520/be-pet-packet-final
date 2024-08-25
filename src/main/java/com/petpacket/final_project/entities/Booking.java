package com.petpacket.final_project.entities;

import java.sql.Timestamp;
import java.util.List;

import com.petpacket.final_project.entities.pet.Pet;
import com.petpacket.final_project.entities.service.Service;
import com.petpacket.final_project.entities.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"Booking\"", schema = "public")
public class Booking {

	@Id
	@Column(name = "booking_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "pet_id", nullable = false)
	private Pet pet;

	@ManyToOne
	@JoinColumn(name = "service_id", nullable = false)
	private Service service;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", name = "booking_date")
	private Timestamp bookingDate;

	@Column(name = "start_booking")
	private Timestamp startBooking;
	
	@Column(name = "end_booking")
	private Timestamp endBooking;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "total_price")
	private Float totalPrice;
	
	@Column(name = "status_paid")
	private Boolean statusPaid;

	@OneToMany(mappedBy = "booking")
	private List<Transaction> transactions;

	public Booking() {
		super();
	}

	public Booking(Integer bookingId, User user, Pet pet, Service service, Timestamp bookingDate,
			Timestamp startBooking, Timestamp endBooking, String note, Float totalPrice, Boolean statusPaid,
			List<Transaction> transactions) {
		super();
		this.bookingId = bookingId;
		this.user = user;
		this.pet = pet;
		this.service = service;
		this.bookingDate = bookingDate;
		this.startBooking = startBooking;
		this.endBooking = endBooking;
		this.note = note;
		this.totalPrice = totalPrice;
		this.statusPaid = statusPaid;
		this.transactions = transactions;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Timestamp getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Timestamp bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Timestamp getStartBooking() {
		return startBooking;
	}

	public void setStartBooking(Timestamp startBooking) {
		this.startBooking = startBooking;
	}

	public Timestamp getEndBooking() {
		return endBooking;
	}

	public void setEndBooking(Timestamp endBooking) {
		this.endBooking = endBooking;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Boolean getStatusPaid() {
		return statusPaid;
	}

	public void setStatusPaid(Boolean statusPaid) {
		this.statusPaid = statusPaid;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

}
