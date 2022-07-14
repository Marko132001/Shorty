package hr.assecosee.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import hr.assecosee.services.ShortyServices;
import hr.assecosee.services.StatisticsService;
import hr.assecosee.shorty.StatisticsResponse;

@RestController
@ComponentScan({"hr.assecosee.services", "hr.assecosee.shorty"})
public class StatisticsController {
	
	private ShortyServices shortService = new ShortyServices();
	
	private static final Logger LOGGER = LogManager.getLogger(StatisticsController.class);
	
	@Autowired
	private StatisticsService statService;
	
	@GetMapping("/statistics")
	public StatisticsResponse getStatistic(@RequestHeader("Authorization") String token) {
		LOGGER.trace("Requesting URL shorting statistic.");
		
		StatisticsResponse returnStatistics = new StatisticsResponse();
		
		if(shortService.checkToken(token)) {
			
			returnStatistics = statService.getStatistic();
			
			LOGGER.debug("Returning statistical information.");
			return returnStatistics;
			
		}
		
		LOGGER.debug("Invalid token");
		returnStatistics = new StatisticsResponse(null, false);
		
		return returnStatistics;
		
	}
	
}
