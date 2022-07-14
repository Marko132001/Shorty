package hr.assecosee.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import hr.assecosee.shorty.User;
import hr.assecosee.shorty.UserRepository;

@Service
public class LoginService {
	
	private static User existUser;
	
	private UserRepository userRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(LoginService.class);
	
	@Autowired
	public LoginService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	
	public boolean authenticate (User user) {
		
		LOGGER.trace("Executing user authentication method...");
		
		LOGGER.debug("Searching for user " + user.getUserName() + " in the database.");
		Optional<User> existingUser = userRepository.findById(user.getUserName());
		
		if(existingUser.isPresent()) {
			
			existUser = existingUser.get();
			
			LOGGER.debug("User found. Validating password.");
			if(BCrypt.checkpw(user.getPassword(), existUser.getPassword())) {
				
				LOGGER.info("Password is valid. User authenticated.");
				return true;
			}		
			
			LOGGER.debug("Password is invalid.");
		}
		
		LOGGER.info("Invalid username or password.");
		return false;
		
	}
	
	public static String base64Encoding (User user) {
		
		LOGGER.trace("Creating authorization token in base64Encoding method...");
		String token;
		
		token = user.getUserName() + ":" + user.getPassword();
		
		token = Base64.encodeBase64String(token.getBytes());
		
		return token;
		
	}


	public static User getExistUser() {
		return existUser;
	}


	public static void setExistUser(User existUser) {
		LoginService.existUser = existUser;
	}


}
