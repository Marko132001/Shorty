package hr.assecosee.shorty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetResponse {
	
	private String userName;
	
	public UserGetResponse(String userName) {
		this.userName = userName;
	}
	
	public UserGetResponse() {
		
	}

}
