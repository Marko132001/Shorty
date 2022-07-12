package hr.assecosee.shorty;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
