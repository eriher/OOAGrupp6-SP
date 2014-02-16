/**
 * Manage the workflow of the client program.
 * 
 * @author David Stromner
 * @version 2013-02-16
 */

package controller;

import model.Communication;
import model.UserHandler;
import view.Window;

public class Workflow {
	private static Workflow workflow;
	private Communication communication;
	private Window window;

	/**
	 * Set up all classes that workflow needs to manage
	 */
	private Workflow() {
		window = new Window();
		window.setView("Login");
		communication = new Communication();

		ActionHandler.getInstance().setWorkflow(this);
		// TODO Better solution to being able to call
		// communication.addObserver(this).
		window.addObserver(communication);
	}
	
	public static Workflow getInstance(){
		if(workflow == null){
			workflow = new Workflow();
		}
		
		return workflow;
	}

	/**
	 * Tries to connect on a new thread to the server.
	 * 
	 * @param username To be sent to the server.
	 * @param password To be sent to the server.
	 */
	public void connectToServer(String username, String password) {
		UserHandler.getInstance().setUsername(username);
		UserHandler.getInstance().setPassword(password);
		communication.requestLogin(username, password);
	}
}
