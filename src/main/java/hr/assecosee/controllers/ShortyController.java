package hr.assecosee.controllers;


import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


import hr.assecosee.services.ShortyServices;
import hr.assecosee.shorty.Shorty;
import hr.assecosee.shorty.ShortyResponse;
import hr.assecosee.shorty.UrlKeyValue;
import hr.assecosee.shorty.UrlRepository;

@RestController
@ComponentScan({"hr.assecosee.services", "hr.assecosee.shorty"})
public class ShortyController {
	
	private UrlRepository urlRepository;
	
	@Autowired
	private ShortyServices shortyService = new ShortyServices(urlRepository);

	
	@PostMapping("/short")
	public ShortyResponse shorting(@RequestBody Shorty shorty, 
								@RequestHeader("Authorization") String token, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		ShortyResponse ret;
		
		if(!shortyService.checkToken(token)) {
			
			ret = new ShortyResponse("Invalid token", false);
			
			return ret;
			
		}
			
		
		if(shortyService.isValid(shorty.getUrl())) {
			
			String rootContext = request.getContextPath();
			
			String shortUrl = shortyService.shortUrl(shorty);
			
			ret = new ShortyResponse(rootContext + "/" + shortUrl, true);
			
			return ret;		
		}
		
		ret = new ShortyResponse("Invalid URL", false);
		
		return ret;
		
	}
	
	
	@GetMapping("/{shortUrl}")
	public void redirect(@PathVariable String shortUrl, HttpServletResponse response) {
		
		UrlKeyValue link = shortyService.exists(shortUrl);
		
		if(link != null) {
			
			response.setHeader("Location", link.getOriginalUrl());
			response.setStatus(link.getRedirectType());
		}
		
		
		
	}
	
	

}
