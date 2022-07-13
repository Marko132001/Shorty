package hr.assecosee.shorty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
public class UrlRepositoryTest {
	
	@Autowired
	private UrlRepository urlRepository;
	
	
	@Test
	void testUpdateNumberOfRedirects() {
		
		UrlKeyValue url = new UrlKeyValue("https://www.overleaf.com/login", "1Japh8h", 
				301, "Marko", 2);
		
		urlRepository.save(url);
			
		urlRepository.updateNumberOfRedirects(url.getOriginalUrl(), url.getUserName(), url.getNumberOfRedirects() + 1);
		
		assertEquals(3, urlRepository.findByoriginalUrlAndUserName(url.getOriginalUrl(), url.getUserName()).getNumberOfRedirects());
		
	}

	
	@Test
	void testFindByOriginalUrlAndUserName() {
		
		UrlKeyValue url = new UrlKeyValue("https://www.overleaf.com/login", "1Japh8h", 
				301, "Marko", 2);
		
		urlRepository.save(url);
		
		assertNotNull(urlRepository.findByoriginalUrlAndUserName(url.getOriginalUrl(), url.getUserName()));
		assertNull(urlRepository.findByoriginalUrlAndUserName(url.getShortUrl(), url.getUserName()));
		
	}
	
	@Test
	void testFindByShortUrlAndUserName() {
		
		UrlKeyValue url = new UrlKeyValue("https://www.overleaf.com/login", "1Japh8h", 
				301, "Marko", 2);
		
		urlRepository.save(url);
		
		assertNotNull(urlRepository.findByshortUrlAndUserName(url.getShortUrl(), url.getUserName()));
		assertNull(urlRepository.findByshortUrlAndUserName(url.getOriginalUrl(), url.getUserName()));
	}
	
	@Test
	void testfindAllByUserName() {
		
		UrlKeyValue url1 = new UrlKeyValue("https://www.overleaf.com/login", "1Japh8h", 
				301, "Marko", 2);
		UrlKeyValue url2 = new UrlKeyValue("https://reflectoring.io/spring-boot-test/", "Ckdit7q", 
				301, "Marko123", 2);
		UrlKeyValue url3 = new UrlKeyValue("https://reflectoring.io/spring-boot-test/", "Ckdit7q", 
				301, "Marko", 2);
		
		urlRepository.save(url1);
		urlRepository.save(url2);
		urlRepository.save(url3);
		
		assertEquals(2, urlRepository.findAllByUserName("Marko").size());
		assertEquals(1, urlRepository.findAllByUserName("Marko123").size());
		assertTrue(urlRepository.findAllByUserName("Marko132001").isEmpty());
	}
	
	

}
