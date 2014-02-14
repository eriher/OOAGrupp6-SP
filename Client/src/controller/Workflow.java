/**
 * Write a description of class Workflow here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

package controller;

import model.Communication;
import model.UserHandler;
import view.Window;

public class Workflow {
	private UserHandler userHandler;
	private Communication communication;
	private Window window;
	
	public Workflow(){
		window = new Window();
		window.setView("Login");
		userHandler = new UserHandler();
		communication = new Communication();
		
		ActionHandler.getInstance().setUserHandler(userHandler);
		ActionHandler.getInstance().setCommunication(communication);
	}
}
