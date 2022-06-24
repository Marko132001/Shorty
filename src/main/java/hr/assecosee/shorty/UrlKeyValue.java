package hr.assecosee.shorty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="url")
public class UrlKeyValue {
	
	@Id
	private String shortUrl;
	private String originalUrl;
	private int redirectType;
	
	public UrlKeyValue(String originalUrl, String shortUrl, int redirectType) {
		
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.redirectType = redirectType;
	}
	
	public UrlKeyValue() {
		
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public int getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(int redirectType) {
		this.redirectType = redirectType;
	}


}
