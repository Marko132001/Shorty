package hr.assecosee.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hr.assecosee.mappers.UserMapper;
import hr.assecosee.services.RegistrationService;
import hr.assecosee.shorty.User;
import hr.assecosee.shorty.UserGetResponse;
import hr.assecosee.shorty.UserRegistrationResponse;

@RestController
@ComponentScan({"hr.assecosee.services", "hr.assecosee.shorty"})
public class RegistrationController {
	
	private UserMapper mapper = new UserMapper();
	
	@Autowired
	private RegistrationService service;

	@GetMapping("/register/{id}")
	public User getUser(@PathVariable String id) { 	
		
		return service.getUserById(id);
		
	}
	

	@PostMapping("/register/add")                    
	public UserRegistrationResponse register(@RequestBody UserGetResponse newUser) {
		
		User user = new User();
		user.setUserName(newUser.getUserName());
		
		Optional<User> optional;
		boolean success;
		
		optional = service.addUser(user);
		
		if(optional.isEmpty()) {
			success = false;
		}
		else {
			success = true;		
		}
		
		
		return mapper.postDTO(user, success);
		
		
	}

}
