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
class StatisticsControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testGetStatistic() {
		
		User user = new User("Marko132001", "$2a$10$myavZgV70WIBWrFe4XoxmOM14n0rknq/SyO8gvtK8NTTgA/361Nbe");
		
		LoginService.setExistUser(user);
		
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer TWFya28xMzIwMDE6VEpPc0dwS1poaA==");
        
        HttpEntity<Void> request = new HttpEntity<>(headers);
	
		ResponseEntity<StatisticsResponse> res = this.restTemplate
				.exchange("http://localhost:" + port + "/shorty/statistics", HttpMethod.GET, request, StatisticsResponse.class);
		
		assertEquals(200, res.getStatusCodeValue());
		assertEquals("Invalid token", res.getBody().getDescription());
		
		
		
	}

}
