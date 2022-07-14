package hr.assecosee.controllers;


import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private static final Logger LOGGER = LogManager.getLogger(ShortyController.class);
	
	@Autowired
	private ShortyServices shortyService = new ShortyServices(urlRepository);

	
	@PostMapping("/short")
	public ShortyResponse shorting(@RequestBody Shorty shorty, 
								@RequestHeader("Authorization") String token, HttpServletRequest request) throws NoSuchAlgorithmException {
		
		LOGGER.trace("Sending a request for URL shorting.");
		ShortyResponse ret;
		
		if(!shortyService.checkToken(token)) {
					
			ret = new ShortyResponse("Invalid token", false);
			
			LOGGER.debug("Invalid token.");
			return ret;
			
		}
			
		LOGGER.trace("Validating URL in isValid method.");
		if(shortyService.isValid(shorty.getUrl())) {
			
			String rootContext = request.getContextPath();
			
			String shortUrl = shortyService.shortUrl(shorty);
			
			LOGGER.trace("Executing shortUrl method.");
				
			ret = new ShortyResponse(rootContext + "/" + shortUrl, true);
			
			LOGGER.debug("Returning shorted URL: " + ret.getShortUrl());		
			return ret;		
		}
		
		LOGGER.debug("Invalid URL.");
		ret = new ShortyResponse("Invalid URL", false);
		
		return ret;
		
	}
	
	
	@GetMapping("/{shortUrl}")
	public void redirect(@PathVariable String shortUrl, HttpServletResponse response) {
		
		LOGGER.trace("Redirecting to original URL.");
		UrlKeyValue link = shortyService.exists(shortUrl);
		
		if(link != null) {
			
			response.setHeader("Location", link.getOriginalUrl());
			response.setStatus(link.getRedirectType());
		}
		
		
		
	}
	
	

}
