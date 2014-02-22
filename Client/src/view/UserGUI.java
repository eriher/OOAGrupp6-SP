/**
 * All unique swing components for the UserGUI.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-12
 */

package view;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ActionHandler;

public abstract class UserGUI extends GUI {
	private static final long serialVersionUID = 3638212974922784125L;

	public UserGUI() {
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
		tempPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		components.put("topPanel", tempPanel);

		// Init sideMenu
		tempPanel = new JPanel();
		tempPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		tempPanel.setLayout(new BorderLayout());
		components.put("menuPanel", tempPanel);

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

		// topPanel
		getCanvas().add(components.get("topPanel"), BorderLayout.NORTH);

		// menuPanel
		getCanvas().add(components.get("menuPanel"), BorderLayout.WEST);

		// topMenuPanel
		components.get("menuPanel").add(topMenuPanel, BorderLayout.NORTH);

		// botMenuPanel
		components.get("menuPanel").add(botMenuPanel, BorderLayout.SOUTH);

		// logOutButton
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0, 0, 5, 0);
		botMenuPanel.add(components.get("logOutButton"), c);
	}
}
