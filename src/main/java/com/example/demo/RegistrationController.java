package com.example.demo;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@RestController
public class RegistrationController {
	
	private Mapper mapper = new Mapper();
	
	@Autowired
	private RegistrationService service;

	@GetMapping("/register/{id}")
	public UserGetResponse getUser(@PathVariable String id) {   //old return: MappingJacksonValue
		
		
		User user = service.getUserbyId(id);
		
		return mapper.getDTO(user);
		
		//alternative solution:
		/*
		FilterProvider filter = new SimpleFilterProvider();
		SimpleBeanPropertyFilter propertyFilter;
		
		propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("userName");
		filter = new SimpleFilterProvider().addFilter("responseFilter", propertyFilter);
		
		MappingJacksonValue value = new MappingJacksonValue(user);
		
		value.setFilters(filter);
		
		return value;
		*/
			
		
	}
	
	//Not required:
/*	
	@GetMapping("/register/all")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
*/	
	@PostMapping("/register/add")
	public UserRegistrationResponse register(@RequestBody User newUser) {
		
		
		service.addUser(newUser);
		
		return mapper.postDTO(newUser);
		
		//alternative solution:
		/*
		FilterProvider filter = new SimpleFilterProvider();
		SimpleBeanPropertyFilter propertyFilter;
		
		if(!newUser.isSuccess()) {
			
			propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("success", "failDescription");		
			
		}
		else {
			
			propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("success", "password");
		}
		
		filter = new SimpleFilterProvider().addFilter("responseFilter", propertyFilter);
		
		MappingJacksonValue value = new MappingJacksonValue(newUser);
		
		value.setFilters(filter);
		
		return value;
		*/
		
		
	}

}
