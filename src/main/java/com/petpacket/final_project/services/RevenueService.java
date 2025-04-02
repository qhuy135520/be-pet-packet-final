package com.petpacket.final_project.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.Enum.EmailTemplate;
import com.petpacket.final_project.dto.response.revenue.AdminRevenueResponse;
import com.petpacket.final_project.entities.Revenue;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.RevenueRepository;
import com.petpacket.final_project.repository.user.UserRepository;
import com.petpacket.final_project.services.booking.BookingService;

import jakarta.mail.MessagingException;

@Service
public class RevenueService {
	@Autowired
	private RevenueRepository revenueRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private BookingService bookingService;

	public AdminRevenueResponse getAllRevenueAdmin(Integer year) {
		List<Revenue> listRevenues = revenueRepository.findAll();

		List<Double> revenueMonth = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		List<Double> revenueBookingMonth = Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
		for (Revenue revenue : listRevenues) {
			if (revenue.getBooking().getTransaction().getTransactionDetail() != null
					&& revenue.getBooking().getTransaction().getTransactionDetail().getPayDate().getYear() == year) {
				int index = revenue.getBooking().getTransaction().getTransactionDetail().getPayDate().getMonthValue() - 1;
				double amountRevenue = revenue.getAmount();
				double amountRevenueBooking = revenue.getBooking().getTransaction().getAmountPaid() - amountRevenue;
				
				revenueMonth.set(index, revenueMonth.get(index)+amountRevenue);
				revenueBookingMonth.set(index, revenueBookingMonth.get(index)+amountRevenueBooking);
				
			}
		}
		return new AdminRevenueResponse(revenueMonth, revenueBookingMonth);

	}

	public List<Revenue> getRevenueByUserId(Integer userId) {
		User user = userRepository.findById(userId).get();

		List<Revenue> listRevenue = revenueRepository.findByUser(user);
		return listRevenue;
	}

	public void setPaidRevenue(Integer userId) throws MessagingException {
		User user = userRepository.findById(userId).get();
		double amount = 0;
		List<Revenue> listRevenue = revenueRepository.findByUser(user);
		for (Revenue revenue : listRevenue) {
			Revenue setRevenue = revenue;
			setRevenue.setStatus("paid");
			revenueRepository.save(setRevenue);

			amount += revenue.getAmount();
		}

		emailService.sendHtmlMail(user.getEmail(), "Payment successfull",
				EmailTemplate.emailInvoiceTemplate(user.getEmail(), user, listRevenue, amount));

	}
}
