/**
 * Write a description of class LoginGUI here.
 * 
 * @author David Stromner
 * @version 2013-02-05
 */

package view;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginGUI extends GUI{	
	public LoginGUI(){
		super();
		initTextFields();
	}
	
	// Init logIn
	protected void initButtons(){
		components.put("logIn", new JButton());
	}
	
	// Init TF name & pass
	private void initTextFields(){
		components.put("userName", new JTextField());
		components.put("password", new JPasswordField());
	}
	
	// Modify & add logIn, userName, password
	protected void buildGUI(){
		
	}
}
