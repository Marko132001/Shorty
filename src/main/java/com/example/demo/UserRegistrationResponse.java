package com.example.demo;

public class UserRegistrationResponse {
	
	private boolean success;
	private String failDescription;
	
	public UserRegistrationResponse(boolean success, String failDescription) {
		
		this.success = success;
		this.failDescription = failDescription;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getFailDescription() {
		return failDescription;
	}
}
