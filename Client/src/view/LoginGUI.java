/**
 * Write a description of class LoginGUI here.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-07
 */

package view;

import java.awt.Component;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends GUI {
	public LoginGUI() {
		super();
		initTextFields();
	}

	// Init logIn
	protected void initButtons() {
		components.put("logIn", new JButton("Log In"));
	}

	// Init TF name & pass
	private void initTextFields() {
		components.put("userName", new JTextField());
		components.put("password", new JPasswordField());
	}

	// Place all components
	protected void buildGUI() {
		super.buildGUI();
	}
}
