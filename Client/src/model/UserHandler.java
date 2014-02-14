/**
 * 
 * 
 * @author David Stromner
 * @version 2013-02-06
 */
/*
 Sparar användarsession
 */

package model;

import java.io.Serializable;

public class UserHandler implements Serializable {
	private static final long serialVersionUID = -1570700443561640144L;
	private String username;
	private String password;
	private static UserHandler userHandler;

	private UserHandler() {
		
	}
	
	public static UserHandler getInstance(){
		if(userHandler == null){
			userHandler = new UserHandler();
		}
		
		return userHandler;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

}
