package hr.assecosee.shorty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Shorty {
	
	private String url;
	private int redirectType = 302;
	
	public Shorty(String url, int redirectType) {
		
		this.url = url;
		this.redirectType = redirectType;
	}
	
	public Shorty() {
		
	}

}
