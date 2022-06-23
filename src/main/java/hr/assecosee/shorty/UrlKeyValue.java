package hr.assecosee.shorty;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="url")
public class UrlKeyValue {
	
	@Id
	private String originalUrl;
	private String shortUrl;
	
	public UrlKeyValue(String originalUrl, String shortUrl) {
		
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
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
}
