package hr.assecosee.shorty;

import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class PasswordGenerator {
	
	private String generatedPassword;
	private String hashedPassword;
	private static Random random = new Random();
	
	public PasswordGenerator() {
		
		this.generatedPassword = randomPassword();
		this.hashedPassword = BCrypt.hashpw(generatedPassword, BCrypt.gensalt(10));
	}
	
	
	private static String randomPassword() {
		
		String upperAlphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    String lowerAlphabet = "abcdefghijklmnopqrstuvwxyz";
	    String numbers = "0123456789";
	    
	    String alphaNumeric = upperAlphabet + lowerAlphabet + numbers;
	    
	    StringBuilder sb = new StringBuilder();    
	    
	    
	    int length = 10;
	    
	    for(int i = 0; i < length; i++) {
	    	
	    	int index = random.nextInt(alphaNumeric.length());
	    	
	    	char randomChar = alphaNumeric.charAt(index);
	    	
	    	sb.append(randomChar);
	    }
	    
	    String randomString = sb.toString();
	    
	    return randomString;
		
	}
	
	


	public String getGeneratedPassword() {
		return generatedPassword;
	}


	public String getHashedPassword() {
		return hashedPassword;
	}
	
	

}
