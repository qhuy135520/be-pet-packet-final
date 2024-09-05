package com.petpacket.final_project.dto;

public class ErrorResponse {
	private String message;
	private String error;
	private int statusCode;

	public ErrorResponse(String message, String error, int statusCode) {
		this.message = message;
		this.error = error;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
}
