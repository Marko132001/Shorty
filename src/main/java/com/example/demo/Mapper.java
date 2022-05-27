package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Mapper {
	
	public Mapper() {
		
	}
	
	public UserGetResponse getDTO(User user) {
		
		String name = user.getUserName();
		
		return new UserGetResponse(name);

	}
	
	public UserRegistrationResponse postDTO(User user) {
		
		boolean success = user.isSuccess();
		String description = user.getFailDescription();
		String password = user.getPassword();
		
		if(!user.isSuccess()) {
			return new UserRegistrationResponse(success, description);
		}
		
		return new UserRegistrationResponse(success, password);
	}
	
	
	

}
