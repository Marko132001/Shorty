package hr.assecosee.shorty;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

}
