package com.petpacket.final_project.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponse {
	private int statusCode;
	private String message;
	private Object data;

	
}
