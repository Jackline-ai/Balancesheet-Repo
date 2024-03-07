package com.balancesheet.model;

public class ApiResponse {
	private String message;
	private int responseCode;

	public ApiResponse(String message, int responseCode) {
		this.message = message;
		this.responseCode = responseCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getResponseCode() {
		return responseCode;
	}

}
