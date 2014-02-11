/**
 * Write a description of class Schedule here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */
/*
 Sparar användarsession
 */

package model;

public class UserHandler {

	private String user;

	public void userHandler(int personnr) {
		user = Integer.toString(personnr);
	}

	String getUser(String personnr) {
		return user;
	}

}
