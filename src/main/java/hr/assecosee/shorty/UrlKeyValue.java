package hr.assecosee.shorty;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
