package com.petpacket.final_project.controllers;

import java.util.Optional;

import javax.management.relation.RoleNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
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
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.user.UserRepository;
import com.petpacket.final_project.services.authentication.OtpService;
import com.petpacket.final_project.services.authentication.ResetPasswordService;
import com.petpacket.final_project.services.authentication.SignInService;
import com.petpacket.final_project.services.authentication.SignUpService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private OtpService otpSignUpService;
	@Autowired
	private OtpService otpResetPasswordService;
	@Autowired
	private SignUpService signUpService;
	@Autowired
	private SignInService signInService;
	@Autowired
	private ResetPasswordService resetPasswordService;

	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest) throws RoleNotFoundException {
		try {
			JwtResponse jwtResponse = signInService.signin(signInRequest);
			SuccessResponse successResponse = new SuccessResponse();
			successResponse.setData(jwtResponse);
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
	public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest) {

		ResponseEntity<?> responseErrorValidate = signUpService.validate(signUpRequest);
		if (responseErrorValidate != null) {
			return responseErrorValidate;
		}

		signUpService.setSignUpRequest(signUpRequest);

		String otp = otpSignUpService.generateOtp(signUpRequest.getEmail());

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setStatusCode(HttpStatus.OK.value());
		successResponse.setMessage("OTP Sended");

		otpSignUpService.sendOtpEmail(signUpRequest.getEmail(), otp);

		return ResponseEntity.ok(successResponse);

	}

	@PostMapping("/verify-otp-signup")
	public ResponseEntity<?> verifyOtpRegister(@RequestBody OtpRequest otpRequest) {

		ResponseEntity<?> responseEntityOTPRequest = otpSignUpService.validate(otpRequest);
		if (responseEntityOTPRequest != null) {
			return responseEntityOTPRequest;
		}

		signUpService.signup(otpRequest.getEmail());

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setStatusCode(HttpStatus.OK.value());
		successResponse.setMessage("registration successful!");

		return ResponseEntity.ok(successResponse);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest forgotPasswordRequest,
			HttpSession httpSession) {

		String email = forgotPasswordRequest.getEmail();
		Optional<User> userOptional = userRepository.findByEmail(email);
		if (userOptional.isEmpty()) {
			ErrorResponse errorResponse = new ErrorResponse("Email not found!", "Bad request",
					HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}

		String otp = otpResetPasswordService.generateOtp(forgotPasswordRequest.getEmail());

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setStatusCode(HttpStatus.OK.value());
		successResponse.setMessage("registration successful!");
		otpResetPasswordService.sendOtpEmail(forgotPasswordRequest.getEmail(), otp);
		return ResponseEntity.ok(successResponse);
	}

	@PostMapping("/verify-otp-reset-password")
	public ResponseEntity<?> verifyOtpResetPassword(@RequestBody OtpRequest otpRequest, HttpSession httpSession) {

		ResponseEntity<?> responseEntityOTPRequest = otpSignUpService.validate(otpRequest);
		if (responseEntityOTPRequest != null) {
			return responseEntityOTPRequest;
		}

		resetPasswordService.setEmailReset(otpRequest.getEmail());

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setStatusCode(HttpStatus.OK.value());
		successResponse.setMessage("OTP is valid, please reset your password");

		return ResponseEntity.ok(successResponse);

	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest,
			HttpSession httpSession) {
		Optional<User> userOptional = userRepository.findByEmail(resetPasswordRequest.getEmail());

		ResponseEntity<?> errorResponse = resetPasswordService.validate(resetPasswordRequest, userOptional);

		if (errorResponse != null) {
			return errorResponse;
		}

		User user = userOptional.get();
		user.setPassword(passwordEncoder.encode(resetPasswordRequest.getNewPassword()));
		userRepository.save(user);

		SuccessResponse successResponse = new SuccessResponse();
		successResponse.setData("");
		successResponse.setStatusCode(HttpStatus.OK.value());
		successResponse.setMessage("Change password successful, Please sign in again to experience system!");
		return ResponseEntity.ok(successResponse);
	}

}