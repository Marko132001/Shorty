package hr.assecosee.shorty;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShortyResponse {
	
	private String shortUrl;
	private String description;
	
	
	public ShortyResponse(String output, boolean check) {
		
		if(check) {
			
			this.shortUrl = output;
		}
		else {
			this.description = output;
		}
	}


	public String getShortUrl() {
		return shortUrl;
	}


	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}

}
