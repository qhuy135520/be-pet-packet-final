package com.petpacket.final_project.controllers.transaction;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.config.VnpayConfig;
import com.petpacket.final_project.dto.request.payment.PaymentBookingRequest;
import com.petpacket.final_project.dto.request.payment.PaymentRequest;
import com.petpacket.final_project.dto.request.payment.UpgradeTransactionRequest;
import com.petpacket.final_project.dto.response.ErrorResponse;
import com.petpacket.final_project.dto.response.SuccessResponse;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	@PostMapping("/create-order")
	public ResponseEntity<?> createOrder(@RequestBody PaymentBookingRequest paymentBookingRequest,
			HttpServletRequest req) throws UnsupportedEncodingException {
		long amount = (long) paymentBookingRequest.getAmountPaid() * 100;
		String vnp_TxnRef = VnpayConfig.getRandomNumber(8);
		String vnp_IpAddr = VnpayConfig.getIpAddress(req);

//		String vnp_ReturnUrl = "http://localhost:8080/api/payment/payment-info/";
//		vnp_ReturnUrl = vnp_ReturnUrl.concat(String.valueOf(paymentRequest.getUserId()));
		String pathVariable = paymentBookingRequest.getListBookingIds().stream().map(String::valueOf)
				.collect(Collectors.joining(","));

		String vnp_ReturnUrl = "http://petserviceconnect.myftp.biz/booking/payment-booking/" + pathVariable;

		Map<String, String> vnp_Params = new HashMap<>();
		vnp_Params.put("vnp_Version", VnpayConfig.vnp_Version);
		vnp_Params.put("vnp_Command", VnpayConfig.vnp_Command);
		vnp_Params.put("vnp_TmnCode", VnpayConfig.vnp_TmnCode);
		vnp_Params.put("vnp_Amount", String.valueOf(amount));
		vnp_Params.put("vnp_CurrCode", "VND");
//		vnp_Params.put("vnp_BankCode", "NCB");
		vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
		vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
		vnp_Params.put("vnp_OrderType", VnpayConfig.orderType);
		vnp_Params.put("vnp_Locale", "vn");
		vnp_Params.put("vnp_ReturnUrl", vnp_ReturnUrl);
		vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

		Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String vnp_CreateDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

		cld.add(Calendar.MINUTE, 15);
		String vnp_ExpireDate = formatter.format(cld.getTime());
		vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

		List fieldNames = new ArrayList<>(vnp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = (String) vnp_Params.get(fieldName);
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		String vnp_SecureHash = VnpayConfig.hmacSHA512(VnpayConfig.secretKey, hashData.toString());
		queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
		String paymentUrl = VnpayConfig.vnp_PayUrl + "?" + queryUrl;
		SuccessResponse successResponse = new SuccessResponse();

		successResponse.setMessage("payment successfully");
		successResponse.setStatusCode(00);
		successResponse.setData(paymentUrl);
		return ResponseEntity.ok(successResponse);
//        com.google.gson.JsonObject job = new JsonObject();
//        job.addProperty("code", "00");
//        job.addProperty("message", "success");
//        job.addProperty("data", paymentUrl);
//        Gson gson = new Gson();
//        resp.getWriter().write(gson.toJson(job));
	}

	@PostMapping("/payment-info/{userId}")
	public ResponseEntity<?> transaction(@PathVariable Integer userId,
			@RequestBody UpgradeTransactionRequest upgradeTransactionRequest) {
		if (upgradeTransactionRequest.getStatus().equals("00")) {

			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setData("");
			successResponse.setMessage("successfully");
			successResponse.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(successResponse);
		} else {
			ErrorResponse errorResponse = new ErrorResponse();
			errorResponse.setError("Bad Request");
			errorResponse.setMessage("payment failure");
			errorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.ok(errorResponse);
		}
	}
}
