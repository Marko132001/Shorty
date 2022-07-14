package hr.assecosee.services;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.assecosee.shorty.StatisticsResponse;
import hr.assecosee.shorty.UrlKeyValue;
import hr.assecosee.shorty.UrlRepository;

@Service
public class StatisticsService {
	
	
	private final UrlRepository urlRepository;
	
	private static final Logger LOGGER = LogManager.getLogger(StatisticsService.class);
	
	@Autowired
	public StatisticsService(UrlRepository urlRepository) {
		
		this.urlRepository = urlRepository;
	}
	
	
	public StatisticsResponse getStatistic() {
		
		
		String userName = LoginService.getExistUser().getUserName();
		
		HashMap<String, Integer> pair = new HashMap<>();
		
		LOGGER.trace("Searching the database for all URL-s that user shorted.");
		for(UrlKeyValue pairElement : urlRepository.findAllByUserName(userName)) {
			
			pair.put(pairElement.getOriginalUrl(), pairElement.getNumberOfRedirects());		
			
		}
		
		LOGGER.debug("Found " + pair.size() + " URL-s under username " + userName);
		
		return new StatisticsResponse(pair, true);

	}
	
}
