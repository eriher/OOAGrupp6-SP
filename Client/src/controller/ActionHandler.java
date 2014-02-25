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
	 * @param workflow
	 *            pointer to the utility class since it got a send and
	 *            disconnect from server help function.
	 */
	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	/**
	 * Open a dialog that retrieves config info from the config.txt file and
	 * display it in two edible text fields.
	 */
	public void networkConfig() {
		workflow.getWindow().getDialog("networkDialog").setVisible(true);
	}

	/**
	 * Take the current information and tries to verify it with the server.
	 * 
	 * @param username
	 *            of the user.
	 * @param password
	 *            of the user.
	 */
	public void logIn(Container username, Container password) {
		JTextField user = (JTextField) username;
		JPasswordField pass = (JPasswordField) password;

		workflow.send("Login", user.getText(), new String(pass.getPassword()));
	}

	/**
	 * Logs out from the server.
	 */
	public void logOut() {
		workflow.disconnectFromServer();
	}

	/**
	 * Tell the server that the current worker started a new shift.
	 */
	public void checkIn() {

	}

	/**
	 * Tell the server that the current worker stopped the current shift.
	 */
	public void checkOut() {

	}

	/**
	 * The create user button in AdminGUI was pressed, display the dialog for
	 * creating a new user.
	 */
	public void createUser() {
		workflow.getWindow().getDialog("createUserDialog").setVisible(true);
	}

	/**
	 * The edit user button in AdminGUI was pressed, display the dialog for
	 * editing a current user.
	 */
	public void editUser() {
		workflow.getWindow().getDialog("editUserDialog").setVisible(true);
	}

	/**
	 * Open Schedule button in AdminGUI was pressed, request another user's
	 * schedule from the server.
	 */
	public void openSchedule() {

	}

	/**
	 * New Schedule button in AdminGUI was pressed, create a new schedule and
	 * send it to the server.
	 */
	public void newSchedule() {

	}

	/**
	 * New time slot button was pressed in AdminGUI, display the dialog for
	 * creating a new time slot.
	 */
	public void newTimeSlot() {

	}

	/**
	 * Change password button was pressed in the EmployeeGUI, display the dialog
	 * for editing once password.
	 */
	public void changePassword() {

	}

	/**
	 * Cancel button in a dialog window was pressed, close the dialog.
	 * 
	 * @param customDialog
	 *            base dialog window for all dialogs.
	 */
	public void dialogCancel(CustomDialog customDialog) {
		customDialog.setVisible(false);
	}

	/**
	 * The ok button in the network dialog was pressed, edit the config.txt with
	 * the new information
	 * 
	 * @param customDialog
	 *            base dialog window for all dialogs..
	 * @param ipText
	 *            ip for the server.
	 * @param portText
	 *            port for the server.
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
	 * The ok button in the create user dialog was pressed, tell the server to
	 * create a new user with the given params.
	 * 
	 * @param customDialog
	 *            base dialog window for all dialogs.
	 * @param usernameText
	 *            the name of the user.
	 * @param passText
	 *            password for the user.
	 * @param authorityText
	 *            what authority level the user should have.
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

	/**
	 * The ok button in the edit user dialog was pressed, tell the server about
	 * the changes to this user.
	 * 
	 * @param customDialog
	 *            base dialog window for all dialogs.
	 * @param usernameText
	 *            name of the user that was edited.
	 * @param passwordText
	 *            new password for the user.
	 * @param confirmPasswordText
	 *            confirm the new password was correctly written.
	 * @param authorityText
	 *            new authority level of the user.
	 */
	public void editUserDialogOk(CustomDialog customDialog,
			Container usernameText, Container passwordText,
			Container confirmPasswordText, Container authorityText) {
		JTextField username = (JTextField) usernameText;
		JPasswordField passwordT = (JPasswordField) passwordText;
		JPasswordField confirmPasswordT = (JPasswordField) confirmPasswordText;
		JComboBox<String> authority = (JComboBox<String>) authorityText;

		String password = new String(passwordT.getPassword());
		String confirmPassword = new String(confirmPasswordT.getPassword());

		if (password.compareTo(confirmPassword) != 0) {
			workflow.getWindow().setErrorMessage("Passwords doesn't match!");
		} else {
			customDialog.setVisible(false);
			workflow.send("EditUser", username.getText(), password,
					authority.getSelectedItem());
		}
	}
}