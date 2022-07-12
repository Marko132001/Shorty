package hr.asseccosee.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import hr.assecosee.shorty.ShortyApplication;
import hr.assecosee.shorty.User;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest(classes = ShortyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/create-data-users.sql")
@Sql(scripts = "/cleanup-data-users.sql", executionPhase = AFTER_TEST_METHOD)
class LoginControllerIntegrationTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void testIntegrationLogin() {
		
		User user = new User("Marko", "SXCfg3cCKs");
		User user2 = new User("Marko132001", "TJOsGpKZhh");
		User invalidUserName = new User("Marko123", "TJOsGpKZhh");
		User invalidPassword = new User("Marko132001", "SXCfg3cCKs");
		
		ResponseEntity<String> res = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/login", user, String.class);
		
		assertEquals(200, res.getStatusCodeValue());
		assertEquals(String.format("Welcome Marko!%nToken: TWFya286U1hDZmczY0NLcw=="), res.getBody());
		
		ResponseEntity<String> res2 = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/login", user2, String.class);
		
		assertEquals(200, res2.getStatusCodeValue());
		assertEquals(String.format("Welcome Marko132001!%nToken: TWFya28xMzIwMDE6VEpPc0dwS1poaA=="), res2.getBody());
		
		
		
		ResponseEntity<String> resUserName = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/login", invalidUserName, String.class);
		
		assertEquals(200, resUserName.getStatusCodeValue());
		assertEquals(String.format("Invalid username or password!"), resUserName.getBody());
		
		ResponseEntity<String> resPassword = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/login", invalidPassword, String.class);
		
		assertEquals(200, resPassword.getStatusCodeValue());
		assertEquals(String.format("Invalid username or password!"), resPassword.getBody());
		
		
	}

}
