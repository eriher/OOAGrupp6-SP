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

	/**
	 * 
	 */
	private static final long serialVersionUID = -1570700443561640144L;
	private String user;
	private String pw;

	public UserHandler(String personnr, String pass) {
		user = personnr;
		pw	 =	pass;
	}

	String getUser() {
		return user;
	}
	
	String getPass() {
		return pw;
	}

}
