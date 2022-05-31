package hr.assecosee.shorty;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.assecosee.mappers.UserMapper;



@RestController
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
	

	@PostMapping("/register/add")
	public UserRegistrationResponse register(@RequestBody User newUser) {
		
		Optional<User> optional;
		boolean success;
		
		optional = service.addUser(newUser);
		
		if(optional.isEmpty()) {
			success = false;
		}
		else {
			success = true;
		}
			
		
		return mapper.postDTO(newUser, success);
		
		
	}

}
