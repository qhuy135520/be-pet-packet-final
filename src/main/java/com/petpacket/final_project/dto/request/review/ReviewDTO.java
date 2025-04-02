package com.petpacket.final_project.dto.request.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
	private Integer rating;
	private String reviewTxt;
	private String username;
	private Integer serviceId;
}
