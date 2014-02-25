/**
 * Each method got a corresponding swing component that calls the method when the swing component is activated.
 * 
 * @author David Stromner
 * @version 2013-02-25
 */

package controller;

import java.awt.Container;

import javax.swing.JComboBox;
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
		workflow.getWindow().getDialog("createUserDialog").setVisible(true);
	}

	/**
	 * Request a user from the server with all paramaters so they can be edited.
	 */
	public void editUser() {
		workflow.getWindow().getDialog("editUserDialog").setVisible(true);
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
	 * @param customDialog
	 */
	public void dialogCancel(CustomDialog customDialog) {
		customDialog.setVisible(false);
	}

	/**
	 * @param ipText
	 * @param passText
	 */
	public void networkDialogOk(CustomDialog customDialog, Container ipText,
			Container portText) {
		JTextField ip = (JTextField) ipText;
		JTextField port = (JTextField) portText;
		customDialog.setVisible(false);

		FileManagement.getInstance().writeStrings("config.txt", ip.getText(),
				port.getText());
	}

	/**
	 * @param customDialog
	 * @param usernameText
	 * @param passText
	 * @param authorityText
	 */
	public void createUserDialogOk(CustomDialog customDialog,
			Container usernameText, Container passText, Container authorityText) {
		JTextField username = (JTextField) usernameText;
		JPasswordField password = (JPasswordField) passText;
		JComboBox<String> authority = (JComboBox<String>) authorityText;
		customDialog.setVisible(false);

		workflow.send("NewUser", username.getText(),
				new String(password.getPassword()), authority.getSelectedItem());
	}
}