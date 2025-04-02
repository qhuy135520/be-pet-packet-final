package com.petpacket.final_project.services;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.Enum.ERole;
import com.petpacket.final_project.entities.UpgradeRequest;
import com.petpacket.final_project.entities.user.Role;
import com.petpacket.final_project.entities.user.User;
import com.petpacket.final_project.repository.UpgradeRequestRepository;
import com.petpacket.final_project.repository.user.PaymentInformationRepository;
import com.petpacket.final_project.repository.user.RoleRepository;
import com.petpacket.final_project.repository.user.UserRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UpgradeRequestService {
	@Autowired
	private UpgradeRequestRepository upgradeRequestRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PaymentInformationRepository paymentInformationRepository;

	public UpgradeRequest getUpgradeRequestByUserId(Integer userId) {
		UpgradeRequest upgradeRequest = upgradeRequestRepository.findUpgradeRequestByUserId(userId);
		if (upgradeRequest != null && upgradeRequest.getStatus().equals("rejected")
				&& Duration.between(upgradeRequest.getApprovedDate(), LocalDateTime.now()).toDays() > 15) {

			upgradeRequestRepository.delete(upgradeRequest);
			paymentInformationRepository.deleteByUserId(userId);
			return null;
		} else {
			return upgradeRequest;
		}
	}

	public List<UpgradeRequest> getAllUpgradeRequest() {
		return upgradeRequestRepository.findAll();
	}

	public UpgradeRequest confirmRequest(Integer id) {
		UpgradeRequest upgradeRequest = upgradeRequestRepository.findById(id).orElse(null);

		User user = upgradeRequest.getUser();
		Role role = roleRepository.findByRoleName(ERole.ROLE_PROVIDER).get();
		user.setRole(role);
		userRepository.save(user);

		upgradeRequest.setStatus("accepted");
		upgradeRequest.setApprovedDate(LocalDateTime.now());
		return upgradeRequestRepository.save(upgradeRequest);
	}

	public UpgradeRequest rejectRequest(Integer id) {
		UpgradeRequest upgradeRequest = upgradeRequestRepository.findById(id).orElse(null);
		upgradeRequest.setStatus("rejected");
		upgradeRequest.setApprovedDate(LocalDateTime.now());
		return upgradeRequestRepository.save(upgradeRequest);
	}
}
