package hr.assecosee.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import hr.assecosee.shorty.UrlKeyValue;
import hr.assecosee.shorty.UrlRepository;
import hr.assecosee.shorty.User;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

	@Mock
	private UrlRepository urlRepository;
	private StatisticsService statisticService;
	
	@BeforeEach
	void setUp() {
		
		statisticService = new StatisticsService(urlRepository);
	}
	
	
	@Test
	void testGetStatistic() {
		
		User user = new User("Marko132001", "$2a$10$myavZgV70WIBWrFe4XoxmOM14n0rknq/SyO8gvtK8NTTgA/361Nbe");
		
		LoginService.setExistUser(user);
		
		UrlKeyValue url1 = new UrlKeyValue("https://www.overleaf.com/login", "1Japh8h", 
				301, user.getUserName(), 2);
		
		UrlKeyValue url2 = new UrlKeyValue("https://reflectoring.io/spring-boot-test/", "Ckdit7q", 
				301, user.getUserName(), 3);
		
		List<UrlKeyValue> list = new ArrayList<>();
		list.add(url1);
		list.add(url2);
		
		when(urlRepository.findAllByUserName(user.getUserName())).thenReturn(list);
		
		HashMap<String, Integer> map = new HashMap<>();
		
		map.put(url1.getOriginalUrl(), url1.getNumberOfRedirects());
		map.put(url2.getOriginalUrl(), url2.getNumberOfRedirects());
		
		assertEquals(2, statisticService.getStatistic().getPairList().size());
		assertTrue(map.equals(statisticService.getStatistic().getPairList()));
		assertTrue(statisticService.getStatistic().getPairList().containsValue(2));
		assertTrue(statisticService.getStatistic().getPairList().containsKey("https://www.overleaf.com/login"));
		
	}

}
