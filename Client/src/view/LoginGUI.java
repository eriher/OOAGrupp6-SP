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
		components.put("logInButton", new JButton("Log In"));
	}

	/**
	 * Create all panels
	 */
	protected void initPanels() {
		super.initPanels();

		JPanel tempPanel;

		// Init logInWindow to put logInPanel, userName, and password into.
		tempPanel = new JPanel();
		components.put("logInWindow", tempPanel);
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.Y_AXIS));

		// Init logInPanel
		tempPanel = new JPanel();
		components.put("logInPanel", tempPanel);
		tempPanel.setLayout(new BoxLayout(tempPanel, BoxLayout.X_AXIS));
	}

	/**
	 * Create all text fields.
	 */
	protected void initTextFields() {
		super.initTextFields();

		// Init userName
		components.put("userNameTF", new JTextField());

		// Init password
		components.put("passwordPF", new JPasswordField());
	}

	/**
	 * Place all components
	 */
	protected void buildGUI() {
		super.buildGUI();
		
		components.get("logInPanel").add(components.get("logInButton"));
		
		components.get("logInWindow").add(components.get("userNameTF"));
		components.get("logInWindow").add(components.get("passwordPF"));
		components.get("logInWindow").add(components.get("logInPanel"));
		
		canvas.add(components.get("logInWindow"), BorderLayout.CENTER);

	}
}
