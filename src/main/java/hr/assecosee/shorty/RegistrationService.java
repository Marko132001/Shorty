package hr.assecosee.shorty;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
	
	
	private UserRepository userRepository;
	
	@Autowired
	public RegistrationService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	public Optional<User> addUser(User user){
		
		if(!userRepository.existsById(user.getUserName())) {
			
			userRepository.save(user);
			
			return Optional.of(user);
		}
		
		
		return Optional.empty();

	}
	
	public User getUserbyId(String id) {
		
			
		return userRepository.findById(id).orElse(null);

	}


	
}
