package com.petpacket.final_project.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petpacket.final_project.Enum.ECity;
import com.petpacket.final_project.dto.CityDTO;
import com.petpacket.final_project.dto.response.SuccessResponse;

@RestController
@RequestMapping("/api/cities")
public class CityController {
	 @GetMapping
	    public ResponseEntity<?> getCities() {
	        List<CityDTO> cities = Stream.of(ECity.values())
	                .map(city -> new CityDTO(city.name(), city.getDisplayName()))
	                .collect(Collectors.toList());
	        
	        SuccessResponse successResponse = new SuccessResponse();
	        successResponse.setData(cities);
	        successResponse.setMessage("City From Vietnam");
	        successResponse.setStatusCode(HttpStatus.OK.value());
	        
	        return ResponseEntity.ok(successResponse);
	    }
}
