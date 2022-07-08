package hr.assecosee.services;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.assecosee.shorty.PasswordGenerator;
import hr.assecosee.shorty.User;
import hr.assecosee.shorty.UserRepository;

@Service
public class RegistrationService {
	
	
	private final UserRepository userRepository;
	
	@Autowired
	public RegistrationService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	
	public Optional<User> addUser(User user){
		
		PasswordGenerator generatePassword = new PasswordGenerator();
		
		if(!userRepository.existsById(user.getUserName())) {
			
			user.setPassword(generatePassword.getHashedPassword());
			
			userRepository.save(user);
			
			user.setPassword(generatePassword.getGeneratedPassword());
			
			return Optional.of(user);
		}
		
		
		return Optional.empty();

	}
	
	public User getUserById(String id) {
		
			
		return userRepository.findById(id).orElse(null);

	}

	
}
