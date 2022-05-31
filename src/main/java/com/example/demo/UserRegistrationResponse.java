package com.example.demo;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationResponse {
	
	
	private boolean success;
	private String password;
	private String failDescription;
	
	public UserRegistrationResponse(boolean success, String output) {
		
		this.success = success;
		if(success) {
			this.password = output;
		}
		else {
			this.failDescription = output;
		}
		
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

	public String getPassword() {
		return password;
	}
}
