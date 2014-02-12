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

	/**
	 * Create all buttons
	 */
	@Override
	protected void initButtons() {
		super.initButtons();
		// Init logout
		components.put("logOut", new JButton("Log Out"));
	}

	/**
	 * Create all panels
	 */
	@Override
	protected void initPanels() {
		super.initPanels();
		// Init top
		components.put("topPanel", new JPanel());
		// Init side-menu
		components.put("menuPanel", new JPanel());
	}

	/**
	 * Place all components
	 */
	@Override
	protected void buildGUI() {
		super.buildGUI();
	}
}
