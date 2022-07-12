package hr.assecosee.shorty;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationResponse {
	
	
	private boolean success;
	private String password;
	private String failDescription;
	
	public UserRegistrationResponse(boolean success, String output) {
		
		this.success = success;
		if(success) {
			this.password = output;
		}
		else {
			this.failDescription = output;
		}
		
	}

}
