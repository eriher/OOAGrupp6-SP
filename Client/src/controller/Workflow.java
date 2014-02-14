/**
 * Manage the workflow of the client program.
 * 
 * @author David Stromner
 * @version 2013-02-14
 */

package controller;

import model.Communication;
import model.UserHandler;
import view.Window;

public class Workflow {
	private UserHandler userHandler;
	private Communication communication;
	private Window window;

	/**
	 * Set up all classes that workflow needs to manage
	 */
	public Workflow() {
		window = new Window();
		window.setView("Login");
		userHandler = new UserHandler();
		communication = new Communication();

		ActionHandler.getInstance().setUserHandler(userHandler);
		ActionHandler.getInstance().setCommunication(communication);
	}
}
