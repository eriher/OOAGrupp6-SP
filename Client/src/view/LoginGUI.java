/**
 * Write a description of class LoginGUI here.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-07
 */

package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends GUI {
	public LoginGUI() {
		super();

		initTextFields();
	}

	/**
	 * Create all labels
	 */
	protected void initLabels() {
		super.initLabels();
		
		// Init IDLabel
		components.put("usernameLabel", new JLabel("Username:"));

		// Init passwordLabel
		components.put("passwordLabel", new JLabel("Password:"));
	}

	/**
	 * Create all buttons
	 */
	protected void initButtons() {
		super.initButtons();

		// Init logIn
		components.put("logInButton", new JButton("Log In"));
	}

	/**
	 * Create all panels
	 */
	protected void initPanels() {
		super.initPanels();

		JPanel tempPanel;

		// Init logInWindow to put logInButton, userName, and password into.
		tempPanel = new JPanel();
		components.put("logInWindow", tempPanel);
		tempPanel.setLayout(new GridBagLayout());

	}

	/**
	 * Create all text fields.
	 */
	protected void initTextFields() {
		super.initTextFields();

		JTextField tempField;

		// Init userName
		tempField = new JTextField();
		components.put("userNameTF", tempField);

		// Init password
		tempField = new JPasswordField();
		components.put("passwordPF", tempField);
	}

	/**
	 * Place all components
	 */
	protected void buildGUI() {
		super.buildGUI();

		JPanel panel = (JPanel) components.get("logInWindow");
		GridBagConstraints c = new GridBagConstraints();

		// usernameLabel
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 10, 0, 0);
		panel.add(components.get("usernameLabel"), c);
		
		// passwordLabel
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0, 10, 0, 0);
		panel.add(components.get("passwordLabel"), c);
		
		// userNameTextField
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 270;
		panel.add(components.get("userNameTF"), c);

		// passwordPasswordField
		c.gridx = 1;
		c.gridy = 1;
		panel.add(components.get("passwordPF"), c);
		c.ipadx = 0;
		
		// logInButton
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.insets = new Insets(16, 0, 0, 0);
		panel.add(components.get("logInButton"), c);
		 
		canvas.add(components.get("logInWindow"), BorderLayout.CENTER);
	}
}
