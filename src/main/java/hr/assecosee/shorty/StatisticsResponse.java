package hr.assecosee.shorty;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatisticsResponse {
	
	private HashMap<String, Integer> pairList = null;
	private String description = null;
	
	public StatisticsResponse(HashMap<String, Integer> pairList, boolean check) {
			
		
		if(check) {
			
			this.pairList = pairList;
		}
		else {
			this.description = "Invalid token";
		}
		
	}

	public HashMap<String, Integer> getPairList() {
		return pairList;
	}

	public void setPairList(HashMap<String, Integer> pairList) {
		this.pairList = pairList;
	}

	public String getDescription() {
		return description;
	}

	
}
