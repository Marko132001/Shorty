package hr.assecosee.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import hr.assecosee.shorty.Shorty;
import hr.assecosee.shorty.UrlKeyValue;
import hr.assecosee.shorty.UrlRepository;
import hr.assecosee.shorty.User;

@ExtendWith(MockitoExtension.class)
class ShortyServiceTest {

	@Mock
	private UrlRepository urlRepository;
	private ShortyServices shortyService;
	
	@BeforeEach
	void setUp() {
		
		shortyService = new ShortyServices(urlRepository);
	}
	
	
	@Test
	void testCheckToken() {
		
		User user = new User("Marko132001", "$2a$10$myavZgV70WIBWrFe4XoxmOM14n0rknq/SyO8gvtK8NTTgA/361Nbe");
		
		LoginService.setExistUser(user);
		
		String header = "Bearer TWFya28xMzIwMDE6VEpPc0dwS1poaA==";
		
		assertTrue(shortyService.checkToken(header));
		
		header = "Bearer TWFya28xMzIwMDE6VEpPc0dfS1poaA==";
		
		assertFalse(shortyService.checkToken(header));
		
	}
	
	@Test
	void testShortUrl() throws NoSuchAlgorithmException {
		
		Shorty shorty = new Shorty("https://www.overleaf.com/login", 301);
		
		User user = new User("Marko132001", "$2a$10$myavZgV70WIBWrFe4XoxmOM14n0rknq/SyO8gvtK8NTTgA/361Nbe");
		
		UrlKeyValue url = new UrlKeyValue("https://www.overleaf.com/login", "1Japh8h", 
				shorty.getRedirectType(), user.getUserName(), 1);
		
		LoginService.setExistUser(user);
		
		//URL doesn't exist:
		
		when(urlRepository.findByoriginalUrlAndUserName(shorty.getUrl(), user.getUserName())).thenReturn(null);
		
		assertEquals("1Japh8h", shortyService.shortUrl(shorty));
		
		verify(urlRepository).save(any(UrlKeyValue.class));
		
		//URL already exists:
		
		when(urlRepository.findByoriginalUrlAndUserName(shorty.getUrl(), user.getUserName())).thenReturn(url);
		
		assertEquals("1Japh8h", shortyService.shortUrl(shorty));
		
		verify(urlRepository).updateNumberOfRedirects(shorty.getUrl(), user.getUserName(), url.getNumberOfRedirects() + 1);
		
	}

}
