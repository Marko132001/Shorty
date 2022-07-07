package hr.assecosee.shorty;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity(name="url")
@IdClass(UrlKeyValue.class)
public class UrlKeyValue implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	@Id
	private String UserName;
	private String shortUrl;
	@Id
	private String originalUrl;
	private int redirectType;
	private int numberOfRedirects;
	
	public UrlKeyValue(String originalUrl, String shortUrl, int redirectType, String UserName, int numberOfRedirects) {
		
		super();
		this.originalUrl = originalUrl;
		this.shortUrl = shortUrl;
		this.redirectType = redirectType;
		this.UserName = UserName;
		this.numberOfRedirects = numberOfRedirects;
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

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String UserName) {
		this.UserName = UserName;
	}

	public int getNumberOfRedirects() {
		return numberOfRedirects;
	}

	public void setNumberOfRedirects(int numberOfRedirects) {
		this.numberOfRedirects = numberOfRedirects;
	}


}
