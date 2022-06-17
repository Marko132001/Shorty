package hr.assecosee.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hr.assecosee.services.ShortyServices;
import hr.assecosee.shorty.Shorty;
import hr.assecosee.shorty.ShortyResponse;

@RestController
public class ShortyController {
	
	private ShortyServices shortyService = new ShortyServices();
	
	@PostMapping("/shorty")
	public ShortyResponse shorting(@RequestBody Shorty shorty, @RequestHeader("Authorization") String token) {
		
		if(shortyService.checkToken(token)) {
			
			ShortyResponse success = shortyService.shortUrl(shorty);
			
			return success;
		}
		
		ShortyResponse failure = new ShortyResponse("Invalid token", false);
		
		return failure;
		
	}
	

}
