package hr.assecosee.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hr.assecosee.shorty.StatisticsResponse;
import hr.assecosee.shorty.UrlKeyValue;
import hr.assecosee.shorty.UrlRepository;

@Service
public class StatisticsService {
	
	
	private UrlRepository urlRepository;
	
	@Autowired
	public StatisticsService(UrlRepository urlRepository) {
		
		this.urlRepository = urlRepository;
	}
	

	
	public StatisticsResponse getStatistic() {
		
		
		String userName = LoginService.getExistUser().getUserName();
		
		List<UrlKeyValue> listUrl = new ArrayList<>();
		
		listUrl = urlRepository.findAllByUserName(userName);
		
		HashMap<String, Integer> pair = new HashMap<>();
		
		for(UrlKeyValue var : listUrl) {
			
			pair.put(var.getOriginalUrl(), var.getNumberOfRedirects());		
			
		}
		
		
		StatisticsResponse retStat = new StatisticsResponse(pair, true);
		
		return retStat;

	}
	
}
