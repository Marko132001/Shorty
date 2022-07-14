package hr.assecosee.services;


import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.assecosee.shorty.PasswordGenerator;
import hr.assecosee.shorty.User;
import hr.assecosee.shorty.UserRepository;

@Service
public class RegistrationService {
	
	
	private final UserRepository userRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(RegistrationService.class);
	
	@Autowired
	public RegistrationService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	
	public Optional<User> addUser(User user){
		
		LOGGER.trace("Executing addUser method...");
		
		PasswordGenerator generatePassword = new PasswordGenerator();
		
		LOGGER.debug("Checking if user " + user.getUserName() + " is registered.");
		if(!userRepository.existsById(user.getUserName())) {
			
			LOGGER.debug("Generating hashed password and saving user data.");
			user.setPassword(generatePassword.getHashedPassword());
			
			userRepository.save(user);
			
			user.setPassword(generatePassword.getGeneratedPassword());
			
			LOGGER.info("User successfully registered.");
			return Optional.of(user);
		}
		
		LOGGER.info("Error! User already exists.");
		return Optional.empty();

	}
	
	public User getUserById(String id) {
		
			
		return userRepository.findById(id).orElse(null);

	}

	
}
