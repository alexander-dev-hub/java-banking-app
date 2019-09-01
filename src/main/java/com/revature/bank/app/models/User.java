package com.revature.bank.app.models;

import java.io.Serializable;

/**
 * 
 * @author Berenice Garcia
 * 08/12/2019 
 */
public class User implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7845424472086182268L;

	private String username;
	
	private String password;
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
