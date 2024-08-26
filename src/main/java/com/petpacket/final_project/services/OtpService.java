package com.petpacket.final_project.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
	@Autowired
	private EmailService emailService;

	private final Map<String, String> otpStorage = new HashMap<>();

	public String generateOtp(String email) {
		String otp = String.valueOf(new Random().nextInt(900000) + 100000);
		otpStorage.put(email, otp);
		return otp;
	}

	public void sendOtpEmail(String email, String otp) {
		String subject = "Your OTP Code";
		String body = "Your OTP code is: " + otp;
		emailService.sendSimpleMail(email, subject, body);
	}

	public void clearOtp(String email) {
		otpStorage.remove(email);
	}

	public boolean validateOtp(String email, String otp) {
		return otp.equals(otpStorage.get(email));
	}
}
