package hr.assecosee.shorty;


public class UserGetResponse {
	
	private String userName;
	
	public UserGetResponse(String userName) {
		this.userName = userName;
	}
	
	public UserGetResponse() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
