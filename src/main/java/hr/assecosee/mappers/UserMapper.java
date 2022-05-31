package hr.assecosee.mappers;

import org.springframework.stereotype.Component;

import hr.assecosee.shorty.User;
import hr.assecosee.shorty.UserGetResponse;
import hr.assecosee.shorty.UserRegistrationResponse;

@Component
public class UserMapper {
	
	public UserMapper() {
		
	}
	
	public UserGetResponse getDTO(User user) {
		
		String name = user.getUserName();
		
		return new UserGetResponse(name);

	}
	
	public UserRegistrationResponse postDTO(User user, boolean success) {
		
		String failDescription = "Account username already exists!";
		
		if(!success) {

			return new UserRegistrationResponse(success, failDescription);
		}
		
		String password = user.getPassword();
		return new UserRegistrationResponse(success, password);
	}
	
	
	

}
