package hr.assecosee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hr.assecosee.services.ShortyServices;
import hr.assecosee.services.StatisticsService;
import hr.assecosee.shorty.StatisticsResponse;
import hr.assecosee.shorty.UrlRepository;

@RestController
@ComponentScan({"hr.assecosee.services", "hr.assecosee.shorty"})
public class StatisticsController {
	
	private ShortyServices shortService = new ShortyServices();
	
	private UrlRepository urlRepository;
	
	@Autowired
	private StatisticsService statService = new StatisticsService(urlRepository);
	
	@GetMapping("/statistics")
	public StatisticsResponse getStatistic(@RequestHeader("Authorization") String token) {
		
		StatisticsResponse retStat;
		
		if(shortService.checkToken(token)) {
			
			retStat = statService.getStatistic();
			
			return retStat;
			
		}
		
		
		retStat = new StatisticsResponse(null, false);
		
		return retStat;
		
	}
	
}
