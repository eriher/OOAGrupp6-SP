/**
 * Write a description of class ActionHandler here.
 * 
 * @author David Stromner
 * @version 2013-02-06
 */

package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ActionHandler {
	private static ActionHandler actionHandler = null;
	private String username, password;

	private ActionHandler() {
		username = "";
		password = "";
	}

	/**
	 * Update userName with any activity that happens to userNameTF
	 * 
	 * @param e
	 *            DocumentEvent that happened
	 */
	public void usernameActivity(DocumentEvent e) {
		username = logString(e);
	}

	/**
	 * Update password with any activity that happens to userNameTF
	 * 
	 * @param e
	 *            DocumentEvent that happened
	 */
	public void passwordActivity(DocumentEvent e) {
		password = logString(e);
	}

	/**
	 * @param e
	 *            DocumentEvent that happened
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
	 * Retrieves the information stored in userName & password and sends it to
	 * the server.
	 */
	public void logIn() {
		System.out.println("Pressing: Log In\n With information Username: "
				+ username + "\tPassword:" + password);
	}

	/**
	 * @return The single instance of the object
	 */
	public static ActionHandler getInstance() {
		if (actionHandler == null) {
			actionHandler = new ActionHandler();
		}

		return actionHandler;
	}
}
