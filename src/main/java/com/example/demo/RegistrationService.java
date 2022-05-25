package com.example.demo;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
	
	
	@Autowired
	private UserRepository userRepository;
	
	public void addUser(User user){
		
		userRepository.save(user);
	}
	
	public User getUserbyId(String id) {
		
			
		return userRepository.findById(id).orElse(null);

	}

	public List<User> getAllUsers() {
		
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		
		return users;
	}
	
}
