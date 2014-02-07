/**
 * Write a description of class LoginGUI here.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-07
 */

package view;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends GUI {
	public LoginGUI() {
		super();
		initTextFields();
	}

	/**
	 * Create all buttons
	 */
	protected void initButtons() {
		super.initButtons();

		// Init logIn
		super.initButtons();
		components.put("logIn", new JButton("Log In"));
	}

	/**
	 * Create all panels
	 */
	protected void initPanels() {
		super.initPanels();
	}

	/**
	 * Create all text fields.
	 */
	protected void initTextFields() {
		super.initTextFields();

		// Init username
		components.put("userName", new JTextField());

		// Init password
		components.put("password", new JPasswordField());
	}

	/**
	 * Place all components
	 */
	protected void buildGUI() {
		super.buildGUI();

	}
}
