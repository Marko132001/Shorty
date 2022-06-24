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
	
	private static String token;
	
	private UserRepository userRepository;
	
	@Autowired
	private LoginService loginService = new LoginService(userRepository);
	
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		
		
		if(loginService.authenticate(user)) {
			
			token = loginService.base64Encoding(user);
			
			
			return String.format("Welcome %s!\nToken: %s", user.getUserName(), LoginController.getToken());
		}
		
		return "Invalid username or password!";
	}


	public static String getToken() {
		return token;
	}

}
