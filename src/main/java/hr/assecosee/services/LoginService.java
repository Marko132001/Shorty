package hr.assecosee.services;

import java.util.Optional;

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
	
	@Autowired
	public LoginService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
	
	
	public boolean authenticate (User user) {
			
		Optional<User> existingUser = userRepository.findById(user.getUserName());
		
		if(existingUser.isPresent()) {
			
			existUser = existingUser.get();
			
			if(BCrypt.checkpw(user.getPassword(), existUser.getPassword())) {
				
				return true;
			}		
			
		}
		
		return false;
		
	}
	
	public static String base64Encoding (User user) {
		
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
