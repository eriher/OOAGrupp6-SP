/**
 * Write a description of class UserGUI here.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-07
 */

package view;

import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class UserGUI extends GUI {
	public UserGUI() {
		super();
	}

	// Init logout
	protected void initButtons() {
		components.put("logOut", new JButton("Log Out"));
	}

	// Init top,menu panel
	protected void initPanels() {
		components.put("topPanel", new JPanel());
		components.put("menuPanel", new JPanel());
	}

	// Place all components
	protected void buildGUI() {
		super.buildGUI();
	}
}
