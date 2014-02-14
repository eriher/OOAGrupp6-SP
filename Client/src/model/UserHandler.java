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
	private String user;
	private String password;

	public UserHandler() {
	}

	public void setUser(String user) {
		this.user = user;
	}
	
	public void setPassword(String passowrd){
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}
