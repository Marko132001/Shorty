package hr.assecosee.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import hr.assecosee.shorty.Shorty;
import hr.assecosee.shorty.UrlKeyValue;
import hr.assecosee.shorty.UrlRepository;

@Service
public class ShortyServices {
	
	private UrlRepository urlRepository;
	
	@Autowired
	public ShortyServices(UrlRepository urlRepository) {
		
		this.urlRepository = urlRepository;
	}
	
	public boolean checkToken(String header) {
		
		
		if(!LoginService.getExistUser().equals(null)) {
				
			StringBuilder sb = new StringBuilder("Bearer");
			
			String[] list_split;	
			
			list_split = header.split(" ");
			
			
			if(list_split[0].contentEquals(sb) && list_split.length == 2) {
				
				String[] name_password;
				
				String decodedBase64 = new String(Base64.decodeBase64(list_split[1].getBytes()));

				name_password = decodedBase64.split(":");			

				if(name_password[0].contains(LoginService.getExistUser().getUserName()) && 
						BCrypt.checkpw(name_password[1], LoginService.getExistUser().getPassword())) {
					
					
					return true;
				}	
				
			}
				
		}
		
		return false;	
	}
	
	
	public String shortUrl(Shorty shorty) throws NoSuchAlgorithmException {
		
		
		String originalUrl = shorty.getUrl();
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		
		byte[] hash = digest.digest(originalUrl.getBytes());
		
		int charNum = 7;
		
		String encodedHash = Base64.encodeBase64String(hash).substring(0, charNum);
		
		UrlKeyValue url = new UrlKeyValue(originalUrl, encodedHash);
		
		urlRepository.save(url);
		
		return encodedHash;
		
	}
	
	

}
