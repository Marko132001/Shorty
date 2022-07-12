package hr.asseccosee.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import hr.assecosee.services.LoginService;
import hr.assecosee.shorty.Shorty;
import hr.assecosee.shorty.ShortyApplication;
import hr.assecosee.shorty.ShortyResponse;
import hr.assecosee.shorty.User;

@SpringBootTest(classes = ShortyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShortyControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testShorting() throws NoSuchAlgorithmException {
		
		Shorty shorty = new Shorty("https://www.overleaf.com/login", 301);
		
		User user = new User("Marko132001", "$2a$10$myavZgV70WIBWrFe4XoxmOM14n0rknq/SyO8gvtK8NTTgA/361Nbe");
		
		LoginService.setExistUser(user);
		
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer TWFya28xMzIwMDE6VEpPc0dwS1poaA==");
        
        HttpEntity<Shorty> request = new HttpEntity<>(shorty, headers);
	
		ResponseEntity<ShortyResponse> res = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/short", request, ShortyResponse.class);
		
		assertEquals(200, res.getStatusCodeValue());
		assertEquals("/shorty/1Japh8h", res.getBody().getShortUrl());
		
		//
		shorty.setUrl("ttps://www.overleaf.com/login");
		
		HttpEntity<Shorty> requestBadUrl = new HttpEntity<>(shorty, headers);
		
		ResponseEntity<ShortyResponse> resBadUrl = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/short", requestBadUrl, ShortyResponse.class);
		
		assertEquals(200, resBadUrl.getStatusCodeValue());
		assertEquals("Invalid URL", resBadUrl.getBody().getDescription());
		
		
		//
		shorty.setUrl("https://www.overleaf.com/login");
		headers.set("Authorization", "Bearer TWFya28xMzIwMDE6VEpPc0dwS1pofA==");
		HttpEntity<Shorty> requestFail = new HttpEntity<>(shorty, headers);
		
		ResponseEntity<ShortyResponse> resFail = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/short", requestFail, ShortyResponse.class);
		
		assertEquals(200, resFail.getStatusCodeValue());
		assertEquals("Invalid token", resFail.getBody().getDescription());
			
		
	}
	
	@Sql(scripts = "/create-data-url.sql")
	@Sql(scripts = "/cleanup-data-url.sql", executionPhase = AFTER_TEST_METHOD)
	@Test
	public void testRedirect() throws Exception {
		
		User user = new User("Marko132001", "$2a$10$myavZgV70WIBWrFe4XoxmOM14n0rknq/SyO8gvtK8NTTgA/361Nbe");
		User user2 = new User("Marko123", "$2a$10$0IM3qvwIZnnAFuGxC38gae2viXOPMaH34O0prxMdjLOzk1BqQmS7i");
		
		LoginService.setExistUser(user);
        
        ResponseEntity<Void> res = this.restTemplate
				.getForEntity("http://localhost:" + port + "/shorty/1Japh8h", null);
        
        assertEquals(301, res.getStatusCodeValue());
        
        LoginService.setExistUser(user2);
        
        ResponseEntity<Void> res2 = this.restTemplate
				.getForEntity("http://localhost:" + port + "/shorty/qGmjdJ6", null);
        
        assertEquals(302, res2.getStatusCodeValue());
        
	}
	


}
