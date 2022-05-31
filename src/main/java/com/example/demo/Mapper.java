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
		
		
		if(!user.isSuccess()) {
			String description = user.getFailDescription();
			return new UserRegistrationResponse(success, description);
		}
		
		String password = user.getPassword();
		return new UserRegistrationResponse(success, password);
	}
	
	
	

}
