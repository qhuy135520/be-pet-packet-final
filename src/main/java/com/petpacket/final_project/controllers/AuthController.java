package com.petpacket.final_project.controllers;

import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
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

import com.petpacket.final_project.dto.ErrorResponse;
import com.petpacket.final_project.dto.SuccessResponse;
import com.petpacket.final_project.dto.authentication.ForgotPasswordRequest;
import com.petpacket.final_project.dto.authentication.JwtResponse;
import com.petpacket.final_project.dto.authentication.OtpRequest;
import com.petpacket.final_project.dto.authentication.ResetPasswordRequest;
import com.petpacket.final_project.dto.authentication.SignInRequest;
import com.petpacket.final_project.dto.authentication.SignUpRequest;
import com.petpacket.final_project.dto.authentication.UserResponse;
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
					new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = jwtUtil.generateJwtToken(authentication);
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

			String roleName = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst()
					.orElse("ROLE_CUSTOMER");

			UserResponse userResponse = new UserResponse();
			userResponse.setUserId(userDetails.getId());
			userResponse.setUsername(userDetails.getUsername());
			userResponse.setEmail(userDetails.getEmail());
			userResponse.setGender(userDetails.getGender());
			userResponse.setPhone(userDetails.getPhone());
			userResponse.setStatus(userDetails.getStatus());
			userResponse.setAddress(userDetails.getAddress());
			userResponse.setRole(roleName);
			userResponse.setName(userDetails.getName());

			JwtResponse res = new JwtResponse();
			res.setAccess_token(jwt);
			res.setUser(userResponse);
			
			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setData(res);
			successResponse.setStatusCode(HttpStatus.OK.value());
			successResponse.setMessage("Fetch Login");

			return ResponseEntity.ok(successResponse);
		} catch (LockedException e) {
			ErrorResponse errorResponse = new ErrorResponse("Account not activated", "Unauthorized",
					HttpStatus.LOCKED.value());
			return ResponseEntity.status(HttpStatus.LOCKED).body(errorResponse);

		} catch (BadCredentialsException e) {
			ErrorResponse errorResponse = new ErrorResponse("Username/Password is incorrect", "Unauthorized",
					HttpStatus.UNAUTHORIZED.value());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		} catch (AuthenticationException e) {
			ErrorResponse errorResponse = new ErrorResponse("Authentication failed due to an unknown error.",
					"Authentication Error", HttpStatus.UNAUTHORIZED.value());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
		}
	}

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest, HttpSession session) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
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

		String userName = signUpRequest.getName();
		if (userName == null|| userName.isBlank() || userName.isEmpty()) {
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

		return ResponseEntity.ok("OTP sended");
	}

	@PostMapping("/verify-otp-signup")
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
		user.setUsername(signUpRequest.getUsername());
		user.setEmail(signUpRequest.getEmail());
		user.setPassword(hashedPassword);
		user.setRole(userRole.get());
		user.setAddress(signUpRequest.getAddress());
		user.setPhone(signUpRequest.getPhone());
		user.setGender(signUpRequest.getGender());
		user.setName(signUpRequest.getName());
		user.setStatus(1);
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
			session.setAttribute("verifiedEmail", email);
			return ResponseEntity.ok("OTP is valid, please reset your password");
		}
	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest,
			HttpSession session) {
		String email = (String) session.getAttribute("verifiedEmail");
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
		session.removeAttribute("vefifiedEmail");

		return ResponseEntity.ok("password updated successfully");
	}

}