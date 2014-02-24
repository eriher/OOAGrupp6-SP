/**
 * Takes care of all events that can be triggered by swing components.
 * 
 * @author David Stromner
 * @version 2013-02-16
 */

package controller;

import java.awt.Container;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.FileManagement;
import view.dialog.CustomDialog;

public class ActionHandler {
	private static ActionHandler actionHandler = null;
	private Workflow workflow;

	private ActionHandler() {
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
	 * Open popup that retrieves config info and display it in two edible text
	 * fields
	 */
	public void networkConfig() {
		workflow.getWindow().getDialog("networkDialog").setVisible(true);
	}

	/**
	 * Retrieves user information and sends it to the communication.
	 */
	public void logIn(Container username, Container password) {
		JTextField user = (JTextField) username;
		JPasswordField pass = (JPasswordField) password;

		workflow.send("Login", user.getText(), new String(pass.getPassword()));
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

	}

	/**
	 * Message the server that the user stopped working.
	 */
	public void checkOut() {

	}

	/**
	 * Message the server that a new user needs to be added.
	 */
	public void createUser() {

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
	
	/**
	 * @param ipText
	 * @param passText
	 */
	public void networkDialogOk(CustomDialog customDialog, Container ipText, Container passText){
		JTextField ip = (JTextField)ipText;
		JTextField pass = (JTextField)passText;
		customDialog.setVisible(false);
		
		FileManagement.getInstance().writeStrings("config.txt", ip.getText(), pass.getText());
	}
}