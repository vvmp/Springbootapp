package com.driversapp.driversapp.view;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLogInView {
	private String userName;
	private String password ;
	
	
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
