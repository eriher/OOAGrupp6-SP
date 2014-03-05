/**
 * Each method got a corresponding swing component that calls the method when the swing component is activated.
 * 
 * @author David Stromner
 * @version 2013-02-25
 */

package controller;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.FileManagement;
import model.User;
import swing.TimeTextPanel;
import view.dialog.CustomDialog;

public class ActionHandler {
	private static ActionHandler actionHandler = null;

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
	 * Confirm that the two strings matches, if not display error message.
	 * 
	 * @param password
	 * @param confirmPassword
	 * @return true if the strings matches, false otherwise.
	 */
	private Boolean checkPassword(String password, String confirmPassword) {
		if (password.compareTo(confirmPassword) == 0) {
			return true;
		}

		Workflow.getInstance().getWindow()
				.setErrorMessage("Passwords doesn't match!");
		return false;
	}

	/**
	 * Delete the user that's sent in as a param on the server.
	 * 
	 * @param customDialog
	 *            base dialog window for all dialogs.
	 * @param usernameText
	 *            name of the user.
	 */
	public void deleteUser(CustomDialog customDialog, Container usernameText) {
		JTextField username = (JTextField) usernameText;
		String user = username.getText();
		if (user.compareTo("") == 0 || user.compareTo(" ") == 0) {
			Workflow.getInstance().getWindow()
					.setErrorMessage("Can't remove empty user!");
		} else {
			customDialog.setVisible(false);
			Workflow.getInstance().getCommunication().send("DeleteUser", user);
		}
	}

	/**
	 * Ask the server to get the specified user.
	 * 
	 * @param usernameBox
	 *            to get the selected user from
	 */
	public void getUser(Container usernameBox) {
		JComboBox username = (JComboBox) usernameBox;

		Workflow.getInstance().getCommunication()
				.send("GetUser", username.getSelectedItem());
	}

	/**
	 * Open a dialog that retrieves config info from the config.txt file and
	 * display it in two edible text fields.
	 */
	public void networkConfigDialog() {
		Workflow.getInstance().getWindow().getDialog("NetworkDialog")
				.setVisible(true);
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

		Workflow.getInstance().getCommunication()
				.send("Login", user.getText(), new String(pass.getPassword()));
	}

	/**
	 * Logs out from the server.
	 */
	public void logOut() {
		Workflow.getInstance().getCommunication().send("Logout");
		Workflow.getInstance().getCommunication().disconnect();
		Workflow.getInstance().getWindow().setView("LoginGUI");
		Workflow.getInstance().setScheduleHandler(null);
	}

	/**
	 * Tell the server that the current worker started a new shift.
	 */
	public void checkIn(Container checkIn, Container checkOut) {
		JButton in = (JButton) checkIn;
		JButton out = (JButton) checkOut;
		in.setEnabled(false);
		out.setEnabled(true);

		Workflow.getInstance().getCommunication().send("CheckIn");
	}

	/**
	 * Tell the server that the current worker stopped the current shift.
	 */
	public void checkOut(Container checkIn, Container checkOut) {
		JButton in = (JButton) checkIn;
		JButton out = (JButton) checkOut;
		in.setEnabled(true);
		out.setEnabled(false);
		Workflow.getInstance().getCommunication().send("CheckOut");
	}
	
	/**
	 * Create a default schedule for the current user in admin.
	 */
	public void createDefaultSchedule(User user){
		Workflow.getInstance().getCommunication().send("CreateDefaultSchedule", user);
		Workflow.getInstance().getCommunication().send("GetUser", user.getName());
	}

	/**
	 * The create user button in AdminGUI was pressed, display the dialog for
	 * creating a new user.
	 */
	public void createUserDialog() {
		Workflow.getInstance().getWindow().getDialog("CreateUserDialog")
				.setVisible(true);
	}

	/**
	 * The edit user button in AdminGUI was pressed, display the dialog for
	 * editing a current user. Request all users from the server.
	 */
	public void editUserDialog() {
		Workflow.getInstance().getWindow().getDialog("EditUserDialog")
				.setVisible(true);
	}

	/**
	 * Get user button in AdminGUI was pressed, open dialog to get a (full)
	 * user.
	 */
	public void getUserDialog() {
		Workflow.getInstance().getWindow().getDialog("GetUserDialog")
				.setVisible(true);
	}

	/**
	 * New time slot button was pressed in AdminGUI, display the dialog for
	 * creating a new time slot.
	 */
	public void newTimeSlotDialog() {
		Workflow.getInstance().getWindow().getDialog("NewTimeSlotDialog")
				.setVisible(true);
	}

	/**
	 * Change password button was pressed in the EmployeeGUI, display the dialog
	 * for editing once password.
	 */
	public void changePasswordDialog() {
		Workflow.getInstance().getWindow().getDialog("ChangePasswordDialog")
				.setVisible(true);
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
	 * The ok button in the change password dialog was pressed, confirm the
	 * password matches and send it to the server.
	 * 
	 * @param customDialog
	 *            base dialog window for all dialogs.
	 * @param passwordText
	 *            new password for the user.
	 * @param confirmPasswordText
	 *            confirm the new password was correctly written.
	 */
	public void getUserDialogOk(CustomDialog customDialog, Container usernameBox) {
		getUser(usernameBox);

		customDialog.setVisible(false);
	}

	/**
	 * The ok button in the change password dialog was pressed, confirm the
	 * password matches and send it to the server.
	 * 
	 * @param customDialog
	 *            base dialog window for all dialogs.
	 * @param passwordText
	 *            new password for the user.
	 * @param confirmPasswordText
	 *            confirm the new password was correctly written.
	 */
	public void passwordDialogOk(CustomDialog customDialog,
			Container passwordText, Container confirmPasswordText) {
		JPasswordField password = (JPasswordField) passwordText;
		JPasswordField confirmPassword = (JPasswordField) confirmPasswordText;

		String pass = new String(password.getPassword());
		String confirmPass = new String(confirmPassword.getPassword());

		if (!checkPassword(pass, confirmPass)) {
			return;
		}

		customDialog.setVisible(false);
		Workflow.getInstance().getCommunication().send("ChangePassword", pass);
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

		User user = new User(username.getText(), new String(
				password.getPassword()), authority.getSelectedItem().toString());
		Workflow.getInstance().getScheduleHandler().setScheduleHandler(user);

		Workflow.getInstance().getCommunication().send("NewUser", user);
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
		JPasswordField password = (JPasswordField) passwordText;
		JPasswordField confirmPassword = (JPasswordField) confirmPasswordText;
		JComboBox<String> authority = (JComboBox<String>) authorityText;

		String pass = new String(password.getPassword());
		String confirmPass = new String(confirmPassword.getPassword());
		String user = username.getText();

		if (!checkPassword(pass, confirmPass)) {
			return;
		}
		if (user.compareTo(" ") == 0 || user.compareTo("") == 0 || user == null) {
			Workflow.getInstance().getWindow()
					.setErrorMessage("No valid user!");
			return;
		}
		customDialog.setVisible(false);
		Workflow.getInstance().getCommunication()
				.send("EditUser", user, pass, authority.getSelectedItem());
	}

	/**
	 * The ok button in the new time slot dialog was pressed, add it to the
	 * current schedule.
	 * 
	 * @param customDialog
	 *            base dialog window for all dialogs.
	 * @param yearComboBox
	 *            the selected year.
	 * @param weekComboBox
	 *            the selected week.
	 * @param dayComboBox
	 *            the selected day.
	 * @param startPanel
	 *            given start time.
	 * @param stopPanel
	 *            given stop time.
	 */
	public void newTimeSlotDialogOk(CustomDialog customDialog,
			Container yearComboBox, Container weekComboBox, int day,
			Container startPanel, Container stopPanel) {

		int year = Integer.parseInt(((JComboBox) yearComboBox)
				.getSelectedItem().toString());
		int week = Integer.parseInt(((JComboBox) weekComboBox)
				.getSelectedItem().toString())-1;
		String start = ((TimeTextPanel) startPanel).getTime();
		String stop = ((TimeTextPanel) stopPanel).getTime();

		// Tell model to add the panel
		Workflow.getInstance().getCommunication()
				.send("NewTimeSlot", year, week, day, start, stop);
		customDialog.setVisible(false);
	}
}