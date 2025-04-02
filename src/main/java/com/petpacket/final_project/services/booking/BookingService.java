package com.petpacket.final_project.services.booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.dto.request.booking.BookingRequest;
import com.petpacket.final_project.entities.Revenue;
import com.petpacket.final_project.entities.booking.Booking;
import com.petpacket.final_project.entities.pet.PetType;
import com.petpacket.final_project.entities.service.ServiceAddon;
import com.petpacket.final_project.entities.service.ServiceDetail;
import com.petpacket.final_project.entities.transaction.Transaction;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.BookingRepository;
import com.petpacket.final_project.repository.RevenueRepository;
import com.petpacket.final_project.repository.pet.PetRepository;
import com.petpacket.final_project.repository.pet.PetTypeRepository;
import com.petpacket.final_project.repository.service.ServiceAddonRepository;
import com.petpacket.final_project.repository.service.ServiceDetailRepository;
import com.petpacket.final_project.repository.service.ServiceRepository;
import com.petpacket.final_project.repository.transaction.TransactionRepository;
import com.petpacket.final_project.repository.user.UserRepository;

@Service
public class BookingService {
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PetRepository petRepository;

	@Autowired
	private ServiceDetailRepository serviceDetailRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private ServiceAddonRepository serviceAddonRepository;

	@Autowired
	private PetTypeRepository petTypeRepository;

	public List<Booking> getAllBookingByStatus() {
		return bookingRepository.findByStatus("success");
	}

	public Booking getOneBooking(Integer userId, Integer serviceId) {
		User user = userRepository.findById(userId).get();
		com.petpacket.final_project.entities.service.Service service = serviceRepository.findById(serviceId).get();

		Booking booking = bookingRepository.findBookingByUserAndService(user, service);
		return booking;

	}

	public List<Booking> getListBookingByUserId(Integer userId) {
		User user = userRepository.findById(userId).get();

		List<Booking> listBookings = bookingRepository.findByUser(user).stream()
				.filter(b -> !b.getStatus().equals("pending")).toList();

		for (Booking booking : listBookings) {
			if ((booking.getStartDate().isEqual(LocalDateTime.now())
					|| booking.getStartDate().isBefore(LocalDateTime.now())) && booking.getStatus().equals("pending")) {
				booking.setStatus("cancel");
			}
			bookingRepository.save(booking);
		}

		return listBookings;

	}

	public Transaction AddBooking(BookingRequest bookingRequest) {
		Double discount =  ((100 - ((double)bookingRequest.getDiscount())) / 100);
		
		com.petpacket.final_project.entities.service.Service service = serviceRepository
				.findById(bookingRequest.getServiceId()).get();
		ServiceDetail serviceDetail = serviceDetailRepository.findById(bookingRequest.getServiceDetailId()).get();
		ServiceAddon serviceAddon = null;
		if (bookingRequest.getServiceAddonId() != null) {
			serviceAddon = serviceAddonRepository.findById(bookingRequest.getServiceAddonId()).get();
		}

		User user = userRepository.findById(bookingRequest.getUserId()).get();

		Booking booking = new Booking();
		booking.setBookingDate(LocalDateTime.now());
		booking.setCreatedAt(LocalDateTime.now());
		booking.setStartDate(bookingRequest.getStartDate());
		if (serviceDetail.getTimeUnit().equals("day")) {
			double decimalDays = bookingRequest.getSession() * serviceDetail.getTimeDuration();
			long days = (long) decimalDays;
			long hours = (long) ((decimalDays - days) * 24);
			booking.setEndDate(booking.getStartDate().plusDays(days).plusHours(hours));
		} else {
			double decimalHours = serviceDetail.getTimeDuration() * bookingRequest.getSession();
			long hours = (long) decimalHours;
			long minutes = (long) ((decimalHours - hours) * 60);
			booking.setEndDate(booking.getStartDate().plusHours(hours).plusMinutes(minutes));
		}
		booking.setNote(bookingRequest.getNote());
		booking.setService(service);
		booking.setSession(bookingRequest.getSession());
		booking.setStatus("pending");
		booking.setUpdatedAt(LocalDateTime.now());
		booking.setUser(user);
		booking.setPetNum(bookingRequest.getPetNum());
		PetType petType = petTypeRepository.findById(bookingRequest.getPetType()).get();
		booking.setPetType(petType);
		bookingRepository.save(booking);

		Transaction transaction = new Transaction();
		transaction.setBooking(booking);
		transaction.setPaymentMethod("VNPAY");
		transaction.setStatus("unpaid");
		transaction.setServiceDetail(serviceAddon != null ? serviceAddon.getServiceAddonName() : "None");
		transaction.setSizePet(serviceDetail.getWeightMin() + "-" + serviceDetail.getWeightMax());

		double amount = (serviceDetail.getPrice() + (serviceAddon != null ? serviceAddon.getServiceAddonPrice() : 0))
				* bookingRequest.getSession() * bookingRequest.getPetNum();
		transaction.setAmountPaid(amount * discount);

		return transactionRepository.save(transaction);

	}

	public List<Booking> getBookingByServiceId(Integer serviceId) {
		com.petpacket.final_project.entities.service.Service service = serviceRepository.findById(serviceId).get();

		return bookingRepository.findByService(service);
	}

	public int getBookingByStartDate(Integer serviceId, LocalDate startDate) {
		com.petpacket.final_project.entities.service.Service service = serviceRepository.findById(serviceId).get();

		List<Booking> listBooking = bookingRepository.findByService(service);
		int petNumAvailable = 0;
		for (Booking booking : listBooking) {
			if (booking.getStartDate().toLocalDate().equals(startDate)) {
				petNumAvailable += booking.getPetNum();
			}
		}

		return petNumAvailable;

	}

	public List<Booking> getBookingOwnService(Integer userId) {

		return bookingRepository.findBookingsByUserId(userId);

	}

	public List<Booking> getBookingOwnServicePending(Integer userId) {
		User user = userRepository.findById(userId).get();

		List<Booking> listBookings = bookingRepository.findByUser(user).stream()
				.filter(b -> b.getStatus().equals("pending")).collect(Collectors.toList());

		listBookings.removeIf(booking -> {
			if (booking.getStartDate().isBefore(LocalDateTime.now())) {
				booking.setStatus("cancel");
				bookingRepository.save(booking);
				return true;
			}
			return false;
		});

		return listBookings;

	}

}
