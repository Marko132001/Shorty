package hr.assecosee.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hr.assecosee.shorty.Shorty;

@RestController
public class ShortyController {
	
	
	@ModelAttribute
	public void setResponseHeader(HttpServletResponse response) {
		
		response.setHeader("Authorization", "Bearer " + LoginController.getToken());
	}
	
	
	@PostMapping("/shorty")
	public Shorty shorting(@RequestBody Shorty shorty) {
		
		//implement url shorting
		//return --> success: shortURL, failure: description
		
		
		return shorty;
		
	}
	

}
