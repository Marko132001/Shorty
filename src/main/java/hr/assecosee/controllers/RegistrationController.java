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
import hr.assecosee.shorty.UserRepository;



@RestController
@ComponentScan({"hr.assecosee.services", "hr.assecosee.shorty"})
public class RegistrationController {
	
	private UserMapper mapper = new UserMapper();
	private UserRepository userRepository;
	
	@Autowired
	private RegistrationService service = new RegistrationService(userRepository);

	@GetMapping("/shorty/register/{id}")
	public User/*UserGetResponse*/ getUser(@PathVariable String id) { 
		
		
		User user = service.getUserbyId(id);
		
		//return mapper.getDTO(user);
		return user;
		
	}
	

	@PostMapping("/shorty/register/add")                    //UserGetResponse --> just userName input
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
