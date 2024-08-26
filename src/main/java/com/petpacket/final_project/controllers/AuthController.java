package com.petpacket.final_project.controllers;

import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.dto.authentication.ForgotPasswordRequest;
import com.petpacket.final_project.dto.authentication.JwtResponse;
import com.petpacket.final_project.dto.authentication.OtpRequest;
import com.petpacket.final_project.dto.authentication.ResetPasswordRequest;
import com.petpacket.final_project.dto.authentication.SignInRequest;
import com.petpacket.final_project.dto.authentication.SignUpRequest;
import com.petpacket.final_project.entities.user.ERole;
import com.petpacket.final_project.entities.user.Role;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.user.RoleRepository;
import com.petpacket.final_project.repository.user.UserRepository;
import com.petpacket.final_project.services.OtpService;
import com.petpacket.final_project.services.UserDetailsImpl;
import com.petpacket.final_project.utils.JwtUtil;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JwtUtil jwtUtil;
	private OtpService otpService;

	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository,
			AuthenticationManager authenticationManager, JwtUtil jwtUtil, OtpService otpService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.otpService = otpService;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) throws RoleNotFoundException {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(signInRequest.getUserName(), signInRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtil.generateJwtToken(authentication);
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			String roleName = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst()
					.orElse("ROLE_CUSTOMER");

			JwtResponse res = new JwtResponse();
			res.setToken(jwt);
			res.setUserId(userDetails.getId());
			res.setUserName(userDetails.getUsername());
			res.setEmail(userDetails.getEmail());
			res.setGender(userDetails.getGender());
			res.setPhone(userDetails.getPhone());
			res.setStatus(userDetails.getStatus());
			res.setAddress(userDetails.getAddress());
			res.setRole(roleName);
			res.setFullName(userDetails.getFullName());
			return ResponseEntity.ok(res);
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("username or password is incorrect");
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest, HttpSession session) {
		if (userRepository.existsByUserName(signUpRequest.getUserName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username is already taken");
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email is already taken");
		}
		String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

		Optional<Role> userRole = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER);
		if (userRole.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Role not found");
		}

		String userFullName = signUpRequest.getFullName();
		if (userFullName.isBlank() || userFullName.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("name is invalid");
		}
		String userPhone = signUpRequest.getPhone();
		if (userPhone.isBlank() || userPhone.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("phone is invalid");
		}
		String userAddress = signUpRequest.getAddress();
		if (userAddress.isBlank() || userAddress.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("address is invalid");
		}
		Integer userGender = signUpRequest.getGender();
		if (userGender == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("gender is invalid");
		}

		String otp = otpService.generateOtp(signUpRequest.getEmail());
		otpService.sendOtpEmail(signUpRequest.getEmail(), otp);
		session.setAttribute("signUpRequest", signUpRequest);
		session.setMaxInactiveInterval(5 * 60);

//        User user = new User();
//        user.setUserName(signUpRequest.getUserName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setPassword(hashedPassword);
//        user.setRole(userRole.get());
//        user.setAddress(userAddress);
//        user.setPhone(userPhone);
//        user.setGender(userGender);
//        user.setFullName(userFullName);
//        user.setStatus(signUpRequest.getStatus());
//        userRepository.save(user);
		return ResponseEntity.ok("OTP sended");
	}

	@PostMapping("/verify-otp-register")
	public ResponseEntity<?> verifyOtpRegister(@RequestBody OtpRequest otpRequest, HttpSession session) {
		if (!otpService.validateOtp(otpRequest.getEmail(), otpRequest.getOtp())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP is incorrect");
		}
		SignUpRequest signUpRequest = (SignUpRequest) session.getAttribute("signUpRequest");
		if (signUpRequest == null || !signUpRequest.getEmail().equals(otpRequest.getEmail())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("registration information is invalid or expired");
		}

		String hashedPassword = passwordEncoder.encode(signUpRequest.getPassword());

		Optional<Role> userRole = roleRepository.findByRoleName(ERole.ROLE_CUSTOMER);
		if (userRole.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("role not found");
		}

		User user = new User();
		user.setUserName(signUpRequest.getUserName());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(hashedPassword);
		user.setRole(userRole.get());
		user.setAddress(signUpRequest.getAddress());
		user.setPhone(signUpRequest.getPhone());
		user.setGender(signUpRequest.getGender());
		user.setFullName(signUpRequest.getFullName());
		user.setStatus(signUpRequest.getStatus());
		userRepository.save(user);

		otpService.clearOtp(otpRequest.getEmail());
		session.removeAttribute("signUpRequest");

		return ResponseEntity.ok("registration successful!");
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest,
			HttpSession session) {
		String email = forgotPasswordRequest.getEmail();
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email does not exist");
		}

		String otp = otpService.generateOtp(forgotPasswordRequest.getEmail());
		session.setAttribute("otpResetPassword", otp);
		session.setAttribute("emailResetPassword", email);
		session.setMaxInactiveInterval(5 * 60);

		otpService.sendOtpEmail(forgotPasswordRequest.getEmail(), otp);

		return ResponseEntity.ok("OTP has been sent to your email");
	}

	@PostMapping("/verify-otp-reset-password")
	public ResponseEntity<String> verifyOtpResetPassword(@RequestBody OtpRequest otpRequest, HttpSession session) {
		String inputOtp = otpRequest.getOtp();
		String sessionOtp = (String) session.getAttribute("otpResetPassword");
		String email = (String) session.getAttribute("emailResetPassword");
		if (!otpRequest.getEmail().equals(email)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email not verified");
		} else if (!inputOtp.equals(sessionOtp)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("OTP is invalid");
		} else {
			return ResponseEntity.ok("OTP is valid, please reset your password");
		}
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest,
			HttpSession session) {
		String email = (String) session.getAttribute("emailResetPassword");
		if (email == null || !email.equals(resetPasswordRequest.getEmail())) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("email not verified");
		}
		if (!resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("password doesn't match");
		}
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email does not exist");
		}

		User user = userOptional.get();
		user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
		userRepository.save(user);

		session.removeAttribute("otpResetPassword");
		session.removeAttribute("emailResetPassword");

		return ResponseEntity.ok("password updated successfully");
	}

}