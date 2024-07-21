package com.petpacket.final_project.entities;

import java.sql.Timestamp;
import java.util.List;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "petId", nullable = false)
	private Pet pet;

	@ManyToOne
	@JoinColumn(name = "serviceId", nullable = false)
	private Service service;

	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp bookingDate;

	private Timestamp startBooking;
	private Timestamp endBooking;
	private String note;
	private Float totalPrice;
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
