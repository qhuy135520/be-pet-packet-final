package com.petpacket.final_project.services.user;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.Enum.ERole;
import com.petpacket.final_project.Enum.EStatus;
import com.petpacket.final_project.dto.request.user.UpgradeRequestDto;
import com.petpacket.final_project.dto.response.revenue.RevenueResponse;
import com.petpacket.final_project.entities.Revenue;
import com.petpacket.final_project.entities.UpgradeRequest;
import com.petpacket.final_project.entities.user.PaymentInformation;
import com.petpacket.final_project.entities.user.Role;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.UpgradeRequestRepository;
import com.petpacket.final_project.repository.user.PaymentInformationRepository;
import com.petpacket.final_project.repository.user.RoleRepository;
import com.petpacket.final_project.repository.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentInformationRepository paymentInformationRepository;

	@Autowired
	private UpgradeRequestRepository upgradeRequestRepository;

	@Autowired
	private RoleRepository roleRepository;

//	private final String CLOUDINARY_URL = "cloudinary://719733478917128:XTs_FxgLPDLsBuMuXwBkNNZJdnU@degcsamx9";

	public User getUserById(Integer id) {
		return userRepository.findById(id).orElse(null);
	}

	public List<RevenueResponse> getUserByRoleProvider() {
		Role roleProvider = roleRepository.findByRoleName(ERole.ROLE_PROVIDER).get();

		List<RevenueResponse> listRevenues = new ArrayList<>();
		List<User> listUsers = userRepository.findByRole(roleProvider);
		for (User user : listUsers) {
			double amount = 0;
			double totalAmount = 0;
			RevenueResponse revenueResponse = new RevenueResponse();
			for (Revenue revenue : user.getRevenues()) {
				if (revenue.getStatus().equals("unpaid")) {
					amount += revenue.getAmount();
					totalAmount += revenue.getBooking().getTransaction().getAmountPaid();
				}
			}
			revenueResponse.setAmount(amount);
			revenueResponse.setUser(user);
			revenueResponse.setTotalAmount(totalAmount);
			listRevenues.add(revenueResponse);
		}

		return listRevenues;
	}

	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	public User saveUser(User user) {
		return userRepository.save(user);
	}

	public User updateUser(Integer id, User user) {
		User existUser = userRepository.findById(id).orElse(null);
		existUser.setName(user.getName());
		existUser.setPhone(user.getPhone());
		existUser.setCity(user.getCity());
		existUser.setUserPicture(user.getUserPicture());

		return userRepository.save(existUser);
	}

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).get();
	}

	public String getCurrentUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			Object principal = authentication.getPrincipal();

			if (principal instanceof UserDetails) {

				return ((UserDetails) principal).getUsername();
			} else {

				return principal.toString();
			}
		}
		return null;
	}

	public void upgradeUserCreateRequest(UpgradeRequestDto upgradeRequestDto) {
		User user = getUserById(upgradeRequestDto.getUserId());

		UpgradeRequest upgradeRequest = new UpgradeRequest();
		upgradeRequest.setRequestDate(LocalDateTime.now());
		upgradeRequest.setStatus("pending");
		upgradeRequest.setUser(user);
		upgradeRequest.setPictureTxt(upgradeRequestDto.getPictureTxt());
		upgradeRequestRepository.save(upgradeRequest);

		PaymentInformation paymentInformation = new PaymentInformation();
		paymentInformation.setAccountName(upgradeRequestDto.getAccountName());
		paymentInformation.setAccountNumber(upgradeRequestDto.getAccountNumber());
		paymentInformation.setBankName(upgradeRequestDto.getBankName());
		paymentInformation
				.setReleaseDate(upgradeRequestDto.getReleaseMonth() + "/" + upgradeRequestDto.getReleaseYear());
		paymentInformation.setUser(user);
		paymentInformationRepository.save(paymentInformation);

	}

	public void upgradeUser(Integer userId) {
		User user = getUserById(userId);
		Optional<Role> userRole = roleRepository.findByRoleName(ERole.ROLE_PROVIDER);
		user.setRole(userRole.get());

		userRepository.save(user);

	}

	public User blockUser(Integer userId) {
		User user = getUserById(userId);
		user.setStatus(EStatus.BANNED);
		return userRepository.save(user);
	}

	public User unBlockUser(Integer userId) {
		User user = getUserById(userId);
		user.setStatus(EStatus.ACTIVE);
		return userRepository.save(user);
	}

}
