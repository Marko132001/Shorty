package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFilter;

@JsonFilter("responseFilter")
@Entity(name="users")
public class User {
	
	@Id
	private String userName;
	private String password;
	public boolean success;
	public String failDescription = "Account username already exists!";


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