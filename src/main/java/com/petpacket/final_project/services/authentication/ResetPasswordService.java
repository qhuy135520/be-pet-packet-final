package com.petpacket.final_project.services.authentication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.dto.request.authentication.ResetPasswordRequest;
import com.petpacket.final_project.dto.response.ErrorResponse;
import com.petpacket.final_project.entities.user.User;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ResetPasswordService {
	private List<EmailDetails> emailReset = new ArrayList<>();

	public boolean isContainEmail(String email) {
		return emailReset.stream().anyMatch(e -> e.getEmail().equals(email));
	}

	public void setEmailReset(String email) {
		emailReset.add(new EmailDetails(email, System.currentTimeMillis()));
	}

	@Scheduled(fixedRate = 60000)
	public void clearExpiredEmails() {
		long currentTime = System.currentTimeMillis();
		emailReset.removeIf(e -> currentTime - e.getTimestamp() > 300000);
	}

	public ResponseEntity<?> validate(ResetPasswordRequest resetPasswordRequest, Optional<User> userOptional) {
		if (!isContainEmail(resetPasswordRequest.getEmail())) {
			ErrorResponse errorResponse = new ErrorResponse("Email not verified!", "Unauthorized",
					HttpStatus.UNAUTHORIZED.value());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
		if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword())) {
			ErrorResponse errorResponse = new ErrorResponse("Password doesn't match", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}

		if (userOptional.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Email not found!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		return null;
	}

	private static class EmailDetails {
		private final String email;
		private final long timestamp;

		public EmailDetails(String email, long timestamp) {
			this.email = email;
			this.timestamp = timestamp;
		}

		public String getEmail() {
			return email;
		}

		public long getTimestamp() {
			return timestamp;
		}
	}
}
