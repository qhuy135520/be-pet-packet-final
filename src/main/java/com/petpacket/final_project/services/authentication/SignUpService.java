package com.petpacket.final_project.services.authentication;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.Enum.ECity;
import com.petpacket.final_project.Enum.EGender;
import com.petpacket.final_project.Enum.ERole;
import com.petpacket.final_project.Enum.EStatus;
import com.petpacket.final_project.dto.request.authentication.SignUpRequest;
import com.petpacket.final_project.dto.response.ErrorResponse;
import com.petpacket.final_project.entities.user.Role;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.user.RoleRepository;
import com.petpacket.final_project.repository.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class SignUpService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private OtpService otpService;

	private Map<String, SignUpRequest> signUpRequestStorage = new ConcurrentHashMap<>();

	public SignUpRequest getSignUpRequest(String email) {
		return this.signUpRequestStorage.get(email);
	}

	public void setSignUpRequest(SignUpRequest signUpRequest) {
		this.signUpRequestStorage.put(signUpRequest.getEmail(), signUpRequest);
	}

	public void signup(String email) {
		SignUpRequest signUpRequest = this.getSignUpRequest(email);

		Optional<Role> userRole = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER);
		String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

		User user = new User();
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(hashedPassword);
		user.setRole(userRole.get());
		user.setCity(signUpRequest.getCity());
		user.setPhone(signUpRequest.getPhone());
		user.setGender(signUpRequest.getGender());
		user.setName(signUpRequest.getName());
		user.setStatus(EStatus.ACTIVE);
		userRepository.save(user);

		otpService.clearOtp(signUpRequest.getEmail());
		this.signUpRequestStorage.remove(email);
	}

	public ResponseEntity<?> validate(SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			ErrorResponse errorResponse = new ErrorResponse("Username is already taken!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			ErrorResponse errorResponse = new ErrorResponse("Email is already taken!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}

		Optional<Role> userRole = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER);
		if (userRole.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Internal server error!", "Internal server error",
					HttpStatus.INTERNAL_SERVER_ERROR.value());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
		}

		String name = signUpRequest.getName();
		if (name == null || name.isBlank() || name.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Name is invalid!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		String userPhone = signUpRequest.getPhone();
		if (userPhone.isBlank() || userPhone.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Phone is invalid!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		ECity userAddress = signUpRequest.getCity();
		if (userAddress == null || userAddress.name().isBlank() || userAddress.name().isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Address is invalid!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		EGender userGender = signUpRequest.getGender();
		if (userGender == null) {
			ErrorResponse errorResponse = new ErrorResponse("Gender is invalid!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		return null;
	}
}
