/**
 * All unique swing components for the EmployeeGUI.
 * 
 * @author David Stromner
 * @version 2013-02-12
 */

package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ActionHandler;

public class EmployeeGUI extends UserGUI {
	public EmployeeGUI() {
		super();
	}

	/**
	 * Create all buttons.
	 */
	@Override
	protected void initButtons() {
		super.initButtons();

		JButton tempButton;

		// Init logOut
		tempButton = new JButton("Log Out");
		tempButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().logOut();
			}
		});
		components.put("logOutButton", tempButton);

		// Init checkIn
		tempButton = new JButton("Check In");
		tempButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().checkIn();
			}
		});
		components.put("checkInButton", tempButton);

		// Init checkOut
		tempButton = new JButton("Check Out");
		tempButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().checkOut();
			}
		});
		components.put("checkOutButton", tempButton);
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
		tempPanel.setLayout(new GridBagLayout());
		components.put("topMenuPanel", tempPanel);
		
		// Init sideMenu
		tempPanel = new JPanel();
		tempPanel.setLayout(new GridBagLayout());
		components.put("botMenuPanel", tempPanel);
	}

	/**
	 * Place all components.
	 */
	@Override
	protected void buildGUI() {
		super.buildGUI();

		JPanel topMenuPanel = (JPanel) components.get("topMenuPanel");
		JPanel botMenuPanel = (JPanel) components.get("botMenuPanel");
		GridBagConstraints c = new GridBagConstraints();

		// checkInButton
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("checkInButton"), c);

		// checkOutButton
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("checkOutButton"), c);

		// logOutButton
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		botMenuPanel.add(components.get("logOutButton"), c);
		
		// topMenuPanel
		components.get("menuPanel").add(topMenuPanel, BorderLayout.NORTH);
		
		// botMenuPanel
		components.get("menuPanel").add(botMenuPanel, BorderLayout.SOUTH);
	}
}
