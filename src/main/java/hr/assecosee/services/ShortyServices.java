package hr.assecosee.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.net.URL;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import hr.assecosee.shorty.Shorty;
import hr.assecosee.shorty.UrlKeyValue;
import hr.assecosee.shorty.UrlRepository;

@Service
public class ShortyServices {
	
	private final UrlRepository urlRepository;

	
	@Autowired
	public ShortyServices(UrlRepository urlRepository) {
		
		this.urlRepository = urlRepository;
	}

	public ShortyServices() {
		this.urlRepository = null;
		
	}
	
	public boolean checkToken(String header) {
		
		
		if(LoginService.getExistUser() != null) {
				
			StringBuilder sb = new StringBuilder("Bearer");
			
			String[] listSplit;	
			
			listSplit = header.split(" ");
			
			
			if(listSplit[0].contentEquals(sb) && listSplit.length == 2) {
				
				String[] namePasswordBuffer;
				
				String decodedBase64 = new String(Base64.decodeBase64(listSplit[1].getBytes()));

				namePasswordBuffer = decodedBase64.split(":");			

				if(namePasswordBuffer[0].contains(LoginService.getExistUser().getUserName()) && 
						BCrypt.checkpw(namePasswordBuffer[1], LoginService.getExistUser().getPassword())) {
					
					
					return true;
				}	
				
			}
				
		}
		
		return false;	
	}
	
	
	public String shortUrl(Shorty shorty) throws NoSuchAlgorithmException {
		
		
		String originalUrl = shorty.getUrl();
		
		String userName = LoginService.getExistUser().getUserName();
		
		UrlKeyValue findUrl = urlRepository.findByoriginalUrlAndUserName(originalUrl, userName);
			
		
		if(findUrl == null) {	
			
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hash = digest.digest(originalUrl.getBytes());
			
			int charNum = 7;
			
			String encodedHash = Base64.encodeBase64String(hash).substring(0, charNum);
			
			UrlKeyValue url = new UrlKeyValue(originalUrl, encodedHash, shorty.getRedirectType(), userName, 1);
			
			urlRepository.save(url);
			
			
			return encodedHash;
		}
		else {			
			
			urlRepository.updateNumberOfRedirects(originalUrl, userName, findUrl.getNumberOfRedirects() + 1);
			
		}
			
		
		return findUrl.getShortUrl();
		
	}
	
	
	public UrlKeyValue exists(String shortUrl) {
		
		
		UrlKeyValue ret = urlRepository.findByshortUrlAndUserName(shortUrl, LoginService.getExistUser().getUserName());
		return ret;
		
	}
	
	
	 public boolean isValid(String url){
	        
	        try {
	            new URL(url).toURI();
	            return true;
	        }
	        catch (Exception e) {
	            return false;
	        }
	        
	 }
	
	

}
