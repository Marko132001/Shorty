package hr.assecosee.services;

import org.apache.catalina.User;
import org.apache.tomcat.util.codec.binary.Base64;

public class ShortyServices {
	
	public static boolean checkToken(String header) {
		
		User currentUser = (User) LoginService.getExistUser();
		
		if(currentUser != null) {
				
			
			String[] list_split;	
			
			list_split = header.split(" ");
			
			
			if(list_split[0] == "Bearer" && list_split.length == 2) {
				
				String[] name_password;
				
				String decodedBase64 = Base64.decodeBase64(list_split[1]).toString();
				
				name_password = decodedBase64.split(":");
				
				
				if(name_password[0] == currentUser.getUsername() && name_password[1] == currentUser.getPassword()) {
					
					return true;
				}
				
				
			}
			
			
		}
		
		return false;
		
	}

}
