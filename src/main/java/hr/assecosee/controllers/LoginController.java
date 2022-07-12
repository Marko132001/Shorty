package hr.assecosee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.assecosee.services.LoginService;
import hr.assecosee.shorty.User;

@RestController
public class LoginController {
	
	private static String token;
	
	
	@Autowired
	private LoginService loginService;
	
	
	@PostMapping("/login")
	public String login(@RequestBody User user) {
		
		
		if(loginService.authenticate(user)) {
			
			token = LoginService.base64Encoding(user);
			
			
			return String.format("Welcome %s!%nToken: %s", user.getUserName(), LoginController.getToken());
		}
		
		return "Invalid username or password!";
	}


	public static String getToken() {
		return token;
	}

}
