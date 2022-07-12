package hr.asseccosee.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import hr.assecosee.shorty.ShortyApplication;
import hr.assecosee.shorty.UserGetResponse;
import hr.assecosee.shorty.UserRegistrationResponse;


@SpringBootTest(classes = ShortyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegistrationControllerIntegrationTest {
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testIntegrationRegisterAdd() throws Exception {
		
		UserGetResponse newUser = new UserGetResponse("Marko");		
	
		ResponseEntity<UserRegistrationResponse> res = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/register/add", newUser, UserRegistrationResponse.class);
		
		assertEquals(200, res.getStatusCodeValue());
		assertEquals(true, res.getBody().isSuccess());
		assertEquals(10, res.getBody().getPassword().length());
		
		res = this.restTemplate
				.postForEntity("http://localhost:" + port + "/shorty/register/add", newUser, UserRegistrationResponse.class);
		assertEquals(200, res.getStatusCodeValue());
		assertEquals(false, res.getBody().isSuccess());
		assertEquals("Account username already exists!", res.getBody().getFailDescription());
	
	}

}
