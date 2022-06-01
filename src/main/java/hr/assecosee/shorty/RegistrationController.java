package hr.assecosee.shorty;




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



@RestController
@ComponentScan({"hr.assecosee.services"})
public class RegistrationController {
	
	private UserMapper mapper = new UserMapper();
	private UserRepository userRepository;
	
	@Autowired
	private RegistrationService service = new RegistrationService(userRepository);

	@GetMapping("/register/{id}")
	public UserGetResponse getUser(@PathVariable String id) { 
		
		
		User user = service.getUserbyId(id);
		
		return mapper.getDTO(user);
			
		
	}
	

	@PostMapping("/register/add")                    //UserGetResponse --> just userName input
	public UserRegistrationResponse register(@RequestBody UserGetResponse newUser) {
		
		PasswordGenerator generatePassword = new PasswordGenerator();
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
			user.setPassword(generatePassword.getGeneratedPassword());
		}
		
		
		return mapper.postDTO(user, success);
		
		
	}

}
