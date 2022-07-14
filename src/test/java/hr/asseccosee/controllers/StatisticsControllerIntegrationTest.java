package hr.asseccosee.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import hr.assecosee.services.LoginService;
import hr.assecosee.shorty.ShortyApplication;
import hr.assecosee.shorty.StatisticsResponse;
import hr.assecosee.shorty.User;

@SpringBootTest(classes = ShortyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/create-data-url.sql")
@Sql(scripts = "/cleanup-data-url.sql", executionPhase = AFTER_TEST_METHOD)
public class StatisticsControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;	
	
	@Test
	public void testGetStatistic() {
		
		User user = new User("Marko132001", "$2a$10$myavZgV70WIBWrFe4XoxmOM14n0rknq/SyO8gvtK8NTTgA/361Nbe");
		
		User user2 = new User("Marko123", "$2a$10$Vwr57dfyjbPTQiTrPNVBNuNFXKTMAvpz30ukw5TZvq2ajQVABQu/u");
		
		LoginService.setExistUser(user);
		
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer TWFya28xMzIwMDE6VEpPc0dwS1poaA==");
        
        HttpEntity<Void> request = new HttpEntity<>(headers);
	
		ResponseEntity<StatisticsResponse> res = this.restTemplate
				.exchange("http://localhost:" + port + "/shorty/statistics", HttpMethod.GET, request, StatisticsResponse.class);
		
		
		assertEquals(200, res.getStatusCodeValue());
		assertEquals(1, res.getBody().getPairList().size());
		assertTrue(res.getBody().getPairList().containsKey("https://www.overleaf.com/login"));
		
		
		LoginService.setExistUser(user2);
				
		headers.set("Authorization", "Bearer TWFya28xMjM6YjQyN3l5Y0JacA==");
		
		request = new HttpEntity<>(headers);
		
		res = this.restTemplate
				.exchange("http://localhost:" + port + "/shorty/statistics", HttpMethod.GET, request, StatisticsResponse.class);
		
		assertEquals(200, res.getStatusCodeValue());
		assertEquals(2, res.getBody().getPairList().size());
		
		
		headers.set("Authorization", "Bearer TWFya28xMjM6YjQyf3l5Y0JacA==");
		
		request = new HttpEntity<>(headers);
		
		res = this.restTemplate
				.exchange("http://localhost:" + port + "/shorty/statistics", HttpMethod.GET, request, StatisticsResponse.class);
		
		assertEquals(200, res.getStatusCodeValue());
		
		assertEquals("Invalid token", res.getBody().getDescription());
		
	}

}
