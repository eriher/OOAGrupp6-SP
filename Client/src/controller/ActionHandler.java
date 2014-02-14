/**
 * Takes care of all events that can be triggered by swing components.
 * 
 * @author David Stromner
 * @version 2013-02-12
 */

package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import model.Communication;
import model.UserHandler;

public class ActionHandler {
	private static ActionHandler actionHandler = null;
	private String username, password;
	private UserHandler userHandler;
	private Communication communication;

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
	 * Set the class instance
	 * 
	 * @param userHandler
	 */
	public void setUserHandler(UserHandler userHandler) {
		this.userHandler = userHandler;
	}
	
	/**
	 * @param communication
	 */
	public void setCommunication(Communication communication){
		this.communication = communication;
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
	 * Retrieves login information and sends it to the server.
	 */
	public void logIn() {
		userHandler.setUser(username);
		userHandler.setPassword(password);
		communication.requestLogin(userHandler);
	}
}
