package com.petpacket.final_project.services.transaction;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.dto.request.transaction.TransactionDetailRequest;
import com.petpacket.final_project.entities.Revenue;
import com.petpacket.final_project.entities.booking.Booking;
import com.petpacket.final_project.entities.transaction.Transaction;
import com.petpacket.final_project.entities.transaction.TransactionDetail;
import com.petpacket.final_project.repository.BookingRepository;
import com.petpacket.final_project.repository.RevenueRepository;
import com.petpacket.final_project.repository.transaction.TransactionDetailRepository;
import com.petpacket.final_project.repository.transaction.TransactionRepository;

@Service
public class TransactionDetailService {

	@Autowired
	private TransactionDetailRepository transactionDetailRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private RevenueRepository revenueRepository;

	public void createTransactionDetail(TransactionDetailRequest transactionDetailRequest)
			throws UnsupportedEncodingException {
		String decodedIds = URLDecoder.decode(transactionDetailRequest.getTransactionId(), "UTF-8");
		List<Integer> idList = Arrays.stream(decodedIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());

		for (Integer id : idList) {
			Booking booking = bookingRepository.findById(id).get();
			booking.setStatus("success");

			Transaction transaction = booking.getTransaction();
			transaction.setStatus("paid");

			transactionRepository.save(transaction);

			TransactionDetail transactionDetail = new TransactionDetail();

			transactionDetail.setBankCode(transactionDetailRequest.getBankCode());
			transactionDetail.setInvoiceNumber(transactionDetailRequest.getInvoiceNumber());
			transactionDetail.setPayDate(LocalDateTime.now());
			transactionDetail.setTransaction(transaction);
			transactionDetail.setTransactionDate(LocalDateTime.now());
			transactionDetail.setTransactionNumber(transactionDetailRequest.getTransactionNumber());

			Revenue revenue = new Revenue();
			revenue.setAmount(transaction.getAmountPaid() * 0.9);
			revenue.setBooking(booking);
			revenue.setStatus("unpaid");
			revenue.setUser(transaction.getBooking().getService().getUser());
			revenueRepository.save(revenue);
			transactionDetailRepository.save(transactionDetail);
		}

	}

}
