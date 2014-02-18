/**
 * All unique swing components for the UserGUI.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-12
 */

package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public abstract class UserGUI extends GUI {
	public UserGUI() {
		super();
	}

	/**
	 * Create all buttons.
	 */
	@Override
	protected void initButtons() {
		super.initButtons();
		// Init logout
		components.put("logOut", new JButton("Log Out"));
	}

	/**
	 * Create all panels.
	 */
	@Override
	protected void initPanels() {
		super.initPanels();
		
		JPanel tempPanel;
		
		// Init top
		tempPanel = new JPanel();
		tempPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		components.put("topPanel", tempPanel);
		
		// Init sideMenu
		tempPanel = new JPanel();
		tempPanel.setLayout(new BorderLayout());
		components.put("menuPanel", tempPanel);
	}

	/**
	 * Place all components.
	 */
	@Override
	protected void buildGUI() {
		super.buildGUI();
		
		// topPanel
		getCanvas().add(components.get("topPanel"), BorderLayout.NORTH);
		
		// menuPanel
		getCanvas().add(components.get("menuPanel"), BorderLayout.WEST);
	}
}
