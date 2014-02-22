/**
 * Takes care of all events that can be triggered by swing components.
 * 
 * @author David Stromner
 * @version 2013-02-16
 */

package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ActionHandler {
	private static ActionHandler actionHandler = null;
	private String username, password;
	private Workflow workflow;

	private ActionHandler() {
		username = "";
		password = "";
	}

	/**
	 * Singleton method.
	 * 
	 * @return The single instance of the object.
	 */
	public static ActionHandler getInstance() {
		if (actionHandler == null) {
			actionHandler = new ActionHandler();
		}

		return actionHandler;
	}

	/**
	 * @param communication
	 */
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * Update userName with any activity that happens to userNameTF.
	 * 
	 * @param e
	 *            DocumentEvent that happened.
	 */
	public void usernameActivity(DocumentEvent e) {
		username = logString(e);
	}

	/**
	 * Update password with any activity that happens to userNameTF.
	 * 
	 * @param e
	 *            DocumentEvent that happened.
	 */
	public void passwordActivity(DocumentEvent e) {
		password = logString(e);
	}

	/**
	 * @param e
	 *            DocumentEvent to get string from.
	 */
	private String logString(DocumentEvent e) {
		Document doc = e.getDocument();
		String s = "";

		try {
			s = doc.getText(0, doc.getLength());
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}

		return s;
	}

	/**
	 * Open popup that retrieves config info and display it in two edible text
	 * fields
	 */
	public void networkConfig() {
		System.out
				.println("There's suppose to be a window here, sadly it's taking a break for the time being");
	}

	/**
	 * Retrieves user information and sends it to the workflow.
	 */
	public void logIn() {
		workflow.send("Login", username, password);
	}

	/**
	 * Retrieves user information and sends it to the workflow.
	 */
	public void logOut() {
		workflow.disconnectFromServer();
	}

	/**
	 * Message the server that the user started working.
	 */
	public void checkIn() {
		workflow.send("checkin");
	}

	/**
	 * Message the server that the user stopped working.
	 */
	public void checkOut() {
		workflow.send("checkout");
	}

	/**
	 * Message the server that a new user needs to be added.
	 */
	public void createUser() {
		workflow.send("createuser");
	}

	/**
	 * Request a user from the server with all paramaters so they can be edited.
	 */
	public void editUser() {

	}

	/**
	 * Request another user's schedule from the server.
	 */
	public void openSchedule() {

	}

	/**
	 * Create a new schedule for the current user.
	 */
	public void newSchedule() {
		
	}

	/**
	 * Create a new time slot for the current week.
	 */
	public void newTimeSlot() {

	}
}
