/**
 * All unique swing components for the AdminGUI.
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

public class AdminGUI extends UserGUI {
	private static final long serialVersionUID = -5722471571581976450L;

	public AdminGUI() {
		super();
	}

	/**
	 * Create all buttons.
	 */
	@Override
	protected void initButtons() {
		super.initButtons();
		
		JButton tempButton;
		
		// Init createUser
		tempButton = new JButton("Create User");
		tempButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ActionHandler.getInstance().createUser();
			}
		});
		components.put("createUserButton", tempButton);
		
		// Init editUser
		tempButton = new JButton("Edit User");
		tempButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ActionHandler.getInstance().editUser();
			}
		});
		components.put("editUserButton", tempButton);
		
		// Init openSchedule
		tempButton = new JButton("Open Schedule");
		tempButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ActionHandler.getInstance().openSchedule();
			}
		});
		components.put("openScheduleButton", tempButton);
		
		// Init newSchedule
		tempButton = new JButton("New Schedule");
		tempButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ActionHandler.getInstance().newSchedule();
			}
		});
		components.put("newScheduleButton", tempButton);
		
		// Init newTimeSlot
		tempButton = new JButton("New Time Slot");
		tempButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				ActionHandler.getInstance().newTimeSlot();
			}
		});
		components.put("newTimeSlotButton", tempButton);
	}

	/**
	 * Place all components.
	 */
	@Override
	protected void buildGUI() {
		super.buildGUI();
		
		JPanel topMenuPanel = (JPanel) components.get("topMenuPanel");
		GridBagConstraints c;
		
		// createUserButton
		c = new GridBagConstraints();
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("createUserButton"), c);
		
		// editUserButtton
		c = new GridBagConstraints();
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("editUserButton"), c);
		
		// openScheduleButton
		c = new GridBagConstraints();
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("openScheduleButton"), c);
		
		// newScheduleButton
		c = new GridBagConstraints();
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("newScheduleButton"), c);
		
		// newTimeSlotButton
		c = new GridBagConstraints();
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5, 0, 0, 0);
		topMenuPanel.add(components.get("newTimeSlotButton"), c);
	}
}
