/**
 * Manage the workflow of the client program.
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
	 * Tries to connect to the server.
	 * 
	 * @param username
	 *            To be sent to the server.
	 * @param password
	 *            To be sent to the server.
	 */
	public void connectToServer(String username, String password) {
		communication.send("login", username, password);
	}

	/**
	 * Disconnect from the server
	 */
	public void disconnectFromServer() {
		communication.disconnect();
		window.setView("Login");
	}
}
