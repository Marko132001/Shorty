package hr.assecosee.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.assecosee.shorty.StatisticsResponse;
import hr.assecosee.shorty.UrlKeyValue;
import hr.assecosee.shorty.UrlRepository;

@Service
public class StatisticsService {
	
	
	private final UrlRepository urlRepository;
	
	@Autowired
	public StatisticsService(UrlRepository urlRepository) {
		
		this.urlRepository = urlRepository;
	}
	
	
	public StatisticsResponse getStatistic() {
		
		
		String userName = LoginService.getExistUser().getUserName();
		
		HashMap<String, Integer> pair = new HashMap<>();
		
		for(UrlKeyValue pairElement : urlRepository.findAllByUserName(userName)) {
			
			pair.put(pairElement.getOriginalUrl(), pairElement.getNumberOfRedirects());		
			
		}
		
		
		return new StatisticsResponse(pair, true);

	}
	
}
