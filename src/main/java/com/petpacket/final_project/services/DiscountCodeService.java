package com.petpacket.final_project.services;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.petpacket.final_project.dto.request.DiscountCodeRequest;
import com.petpacket.final_project.dto.response.SuccessResponse;
import com.petpacket.final_project.entities.DiscountCode;
import com.petpacket.final_project.entities.service.ServiceDetail;
import com.petpacket.final_project.repository.DiscountCodeRepository;
import com.petpacket.final_project.repository.service.ServiceRepository;

@Service
public class DiscountCodeService {

	@Autowired
	private DiscountCodeRepository discountCodeRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	public List<DiscountCode> getDiscountCodeByServiceId(Integer serviceId) {
		com.petpacket.final_project.entities.service.Service service = serviceRepository.findById(serviceId).get();

		List<DiscountCode> listDiscountCodes = discountCodeRepository.findByService(service);

		listDiscountCodes.sort(Comparator.comparing((DiscountCode d) -> d.getStartDate()));

		return listDiscountCodes;
	}

	public List<DiscountCode> createVoucher(DiscountCodeRequest discountCodeRequest) {
		com.petpacket.final_project.entities.service.Service service = serviceRepository
				.findById(discountCodeRequest.getServiceId()).get();

		DiscountCode discountCodeExisted = discountCodeRepository.findByCode(discountCodeRequest.getCode());
		if (discountCodeExisted != null) {
			return null;
		} else {
			DiscountCode discountCode = new DiscountCode();
			discountCode.setCode(discountCodeRequest.getCode());
			discountCode.setDiscountValue(discountCodeRequest.getDiscountValue());
			discountCode.setStartDate(LocalDateTime.of(discountCodeRequest.getStartDate(), LocalTime.of(0, 0)));
			discountCode.setEndDate(LocalDateTime.of(discountCodeRequest.getEndDate(), LocalTime.of(0, 0)));
			discountCode.setUsageLimit(discountCodeRequest.getUsageLimit());
			discountCode.setService(service);
			discountCodeRepository.save(discountCode);

			List<DiscountCode> listDiscountCodes = discountCodeRepository.findByService(service);
			return listDiscountCodes;
		}

	}

	public List<DiscountCode> updateStatusVoucher(Integer disCountCodeId) {
		DiscountCode discountCode = discountCodeRepository.findById(disCountCodeId).get();

		discountCode.setIsActive(!discountCode.getIsActive());
		discountCodeRepository.save(discountCode);

		List<DiscountCode> listDiscountCodes = discountCodeRepository.findByService(discountCode.getService());

		return listDiscountCodes;
	}

	public DiscountCode getDiscountByCode(String code) {
		return discountCodeRepository.findByCode(code);
	}
}
