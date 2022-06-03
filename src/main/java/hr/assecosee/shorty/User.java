package hr.assecosee.shorty;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity(name="users")
public class User {
	
	@Id
	private String userName;
	private String password;


	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	public User() {
		
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	

}
