package hr.assecosee.services;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCrypt;

import hr.assecosee.shorty.Shorty;
import hr.assecosee.shorty.ShortyResponse;

public class ShortyServices {
	
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
	
	
	public ShortyResponse shortUrl(Shorty shorty) {
		
		ShortyResponse ret = new ShortyResponse(shorty.getUrl(), true);
		//Shorting URL implementation
		
		return ret;
		
	}
	
	

}
