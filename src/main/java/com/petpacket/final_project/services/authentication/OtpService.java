package com.petpacket.final_project.services.authentication;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.Enum.ERole;
import com.petpacket.final_project.dto.request.authentication.OtpRequest;
import com.petpacket.final_project.dto.response.ErrorResponse;
import com.petpacket.final_project.entities.user.Role;
import com.petpacket.final_project.repository.user.RoleRepository;
import com.petpacket.final_project.services.EmailService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OtpService {
	@Autowired
	private EmailService emailService;

	@Autowired
	private RoleRepository roleRepository;

	private final Map<String, OtpDetails> otpStorage = new ConcurrentHashMap<>();

	public String generateOtp(String email) {
		String otp = String.valueOf(new Random().nextInt(900000) + 100000);
		otpStorage.put(email, new OtpDetails(otp, System.currentTimeMillis()));
		return otp;
	}

	public boolean validateOtp(String email, String otp) {
		OtpDetails otpDetails = otpStorage.get(email);
		return otpDetails != null && otp.equals(otpDetails.getOtp());
	}

	public void sendOtpEmail(String email, String otp) {
		String subject = "Your OTP Code";
		String body = "Your OTP code is: " + otp;
		emailService.sendSimpleMail(email, subject, body);
	}

	public void clearOtp(String email) {
		otpStorage.remove(email);
	}

	public ResponseEntity<?> validate( OtpRequest otpRequest) {

		Optional<Role> userRole = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER);

		if (!validateOtp(otpRequest.getEmail(), otpRequest.getOtp())) {
			ErrorResponse errorResponse = new ErrorResponse("OTP is incorrect or expired!!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}

		if (userRole.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Internal server error", "Internal Server error",
					HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}
		return null;
	}

	@Scheduled(fixedRate = 60000)
	public void clearExpiredOtps() {
		long currentTime = System.currentTimeMillis();
	    otpStorage.entrySet().removeIf(entry -> currentTime - entry.getValue().getTimestamp() > 300000);
	}

	private static class OtpDetails {
		private final String otp;
		private final long timestamp;

		public OtpDetails(String otp, long timestamp) {
			this.otp = otp;
			this.timestamp = timestamp;
		}

		public String getOtp() {
			return otp;
		}

		public long getTimestamp() {
			return timestamp;
		}
	}

}
