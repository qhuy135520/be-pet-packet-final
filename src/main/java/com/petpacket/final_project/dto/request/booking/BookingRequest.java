package com.petpacket.final_project.dto.request.booking;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingRequest {
	private Integer userId;
	private Integer serviceId;
//	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
	private LocalDateTime startDate;
	private String note;
	private Integer session;
	private Integer serviceDetailId;
	private Integer serviceAddonId;
	private Integer petNum;
	private Integer petType;
	private Integer discount;

}
