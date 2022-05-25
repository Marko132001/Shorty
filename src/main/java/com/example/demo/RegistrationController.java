package com.example.demo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
	
	@Autowired
	private RegistrationService service;

	@GetMapping("/register/{id}")
	public User getUser(@PathVariable String id) {
		

		return service.getUserbyId(id);
		
	}
	
	@GetMapping("/register/all")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	@PostMapping("/register/add")
	public void register(@RequestBody User newUser) {
			
		service.addUser(newUser);
		
		
	}

}
