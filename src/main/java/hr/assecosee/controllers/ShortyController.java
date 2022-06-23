package hr.assecosee.controllers;


import java.security.NoSuchAlgorithmException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hr.assecosee.services.ShortyServices;
import hr.assecosee.shorty.Shorty;
import hr.assecosee.shorty.ShortyResponse;
import hr.assecosee.shorty.UrlRepository;

@RestController
@ComponentScan({"hr.assecosee.services", "hr.assecosee.shorty"})
public class ShortyController {
	
	private UrlRepository urlRepository;
	
	@Autowired
	private ShortyServices shortyService = new ShortyServices(urlRepository);
	

	
	@PostMapping("/shorty/short")
	public ShortyResponse shorting(@RequestBody Shorty shorty, @RequestHeader("Authorization") String token) throws NoSuchAlgorithmException {
		
		
		if(shortyService.checkToken(token)) {
			
			
			String shortUrl = shortyService.shortUrl(shorty);
			
			ShortyResponse success = new ShortyResponse(shortUrl, true);
			
			return success;
		}
		
		ShortyResponse failure = new ShortyResponse("Invalid token", false);
		
		return failure;
		
	}
	

}
