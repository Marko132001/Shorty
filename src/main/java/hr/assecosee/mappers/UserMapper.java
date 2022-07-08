package hr.assecosee.mappers;

import org.springframework.stereotype.Component;

import hr.assecosee.shorty.User;
import hr.assecosee.shorty.UserRegistrationResponse;

@Component
public class UserMapper {
	
	public UserMapper() {
		
	}

	public UserRegistrationResponse postDTO(User user, boolean success) {
		
		if(!success) {

			return new UserRegistrationResponse(success, "Account username already exists!");
		}
		
		return new UserRegistrationResponse(success, user.getPassword());
	}	

}
