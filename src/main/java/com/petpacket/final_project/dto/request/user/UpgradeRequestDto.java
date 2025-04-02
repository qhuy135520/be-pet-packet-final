package com.petpacket.final_project.dto.request.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpgradeRequestDto {
	private String bankName;
	private String accountNumber;
	private String accountName;
	private String releaseMonth;
	private String releaseYear;
	private Integer userId;
	private String pictureTxt;
}
