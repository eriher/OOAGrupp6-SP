/**
 * Initiate the program and manage the flow of the program. Contains a help method for the other classes to send objects to the server.
 * 
 * @author David Stromner
 * @version 2013-02-16
 */

package controller;

import model.Communication;
import view.Window;

public class Workflow {
	private static Workflow workflow;
	private Communication communication;
	private Window window;

	/**
	 * Set up all classes that workflow needs to manage
	 */
	private Workflow() {
		communication = new Communication();
		new Thread() {
			public void run() {
				window = new Window();
				window.setView("Login");
				// TODO Better solution to being able to call
				// communication.addObserver(this).
				window.addObserver(communication);
			}
		}.start();
		ActionHandler.getInstance().setWorkflow(this);
	}

	public static Workflow getInstance() {
		if (workflow == null) {
			workflow = new Workflow();
		}

		return workflow;
	}
	
	/**
	 * @return instance of the window.
	 */
	public Window getWindow(){
		return window;
	}

	/**
	 * Help method for outside methods to send to server.
	 * 
	 * @param args 
	 */
	public void send(Object ... args) {
		communication.send(args);
	}

	/**
	 * Disconnect from the server
	 */
	public void disconnectFromServer() {
		communication.disconnect();
		window.setView("Login");
	}
}