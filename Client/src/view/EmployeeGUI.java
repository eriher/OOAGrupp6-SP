/**
 * All unique swing components for the EmployeeGUI.
 * 
 * @author David Stromner
 * @version 2013-02-12
 */

package view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.ActionHandler;

public class EmployeeGUI extends UserGUI {
	private static final long serialVersionUID = -6805802081178406024L;

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
	 * Place all components.
	 */
	@Override
	protected void buildGUI() {
		super.buildGUI();

		JPanel topMenuPanel = (JPanel) components.get("topMenuPanel");
		GridBagConstraints c;

		// checkInButton
		c = new GridBagConstraints();
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("checkInButton"), c);

		// checkOutButton
		c = new GridBagConstraints();
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("checkOutButton"), c);
	}
}
