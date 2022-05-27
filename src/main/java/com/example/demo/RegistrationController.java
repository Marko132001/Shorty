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
	
	@Autowired
	private RegistrationService service;

	@GetMapping("/register/{id}")
	public MappingJacksonValue getUser(@PathVariable String id) {
		
		User user = service.getUserbyId(id);
		
		FilterProvider filter = new SimpleFilterProvider();
		SimpleBeanPropertyFilter propertyFilter;
		
		propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("userName");
		filter = new SimpleFilterProvider().addFilter("responseFilter", propertyFilter);
		
		MappingJacksonValue value = new MappingJacksonValue(user);
		
		value.setFilters(filter);
		
		return value;

		
	}
/*	
	@GetMapping("/register/all")
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
*/	
	@PostMapping("/register/add")
	public MappingJacksonValue register(@RequestBody User newUser) {
		
		
		service.addUser(newUser);
		
		FilterProvider filter = new SimpleFilterProvider();
		SimpleBeanPropertyFilter propertyFilter;
		
		if(!newUser.success) {
			
			propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("success", "failDescription");		
			
		}
		else {
			
			propertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("success", "password");
		}
		
		filter = new SimpleFilterProvider().addFilter("responseFilter", propertyFilter);
		
		MappingJacksonValue value = new MappingJacksonValue(newUser);
		
		value.setFilters(filter);
		
		return value;
		
	}

}
