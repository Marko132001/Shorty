package hr.assecosee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.assecosee.services.LoginService;
import hr.assecosee.shorty.User;
import hr.assecosee.shorty.UserRepository;

@RestController
public class LoginController {
	
	private UserRepository userRepository;
	
	@Autowired
	private LoginService loginService = new LoginService(userRepository);
	
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		
		User existingUser = loginService.authenticate(user);
		
		if(existingUser != null) {
			
			
			existingUser.setToken(loginService.base64Encoding(user));
			
			return String.format("Welcome %s!", user.getUserName());
		}
		
		return "Invalid username or password!";
	}

}
