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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import controller.ActionHandler;

public class LoginGUI extends GUI {
	public LoginGUI() {
		super();
	}

	/**
	 * Create all labels
	 */
	@Override
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
	@Override
	protected void initButtons() {
		super.initButtons();

		JButton tempButton;

		// Init logIn
		tempButton = new JButton("Log In");
		components.put("logInButton", tempButton);
		tempButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().logIn();
			}
		});

	}

	/**
	 * Create all panels
	 */
	@Override
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
	@Override
	protected void initTextFields() {
		super.initTextFields();

		JTextField tempField;

		// Init userName
		tempField = new JTextField();
		components.put("usernameTF", tempField);
		tempField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				ActionHandler.getInstance().usernameActivity(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				ActionHandler.getInstance().usernameActivity(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				ActionHandler.getInstance().usernameActivity(e);
			}
		});

		// Init password
		tempField = new JPasswordField();
		components.put("passwordPF", tempField);
		tempField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				ActionHandler.getInstance().passwordActivity(e);
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				ActionHandler.getInstance().passwordActivity(e);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				ActionHandler.getInstance().passwordActivity(e);
			}
		});
	}

	/**
	 * Place all components
	 */
	@Override
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
		panel.add(components.get("usernameTF"), c);

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
