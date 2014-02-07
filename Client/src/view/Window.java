/**
 * Write a description of class Window here.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-07
 */

package view;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;

public class Window {
	private HashMap<String, GUI> interfaceList;
	private JFrame frame;

	
	public Window() {
		interfaceList = new HashMap<String, GUI>();
		createFrame();
		// Init all the views
		initViews();
		frame.pack();
	}

	private void createFrame() {
		frame = new JFrame();
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
	}

	private void initViews() {
		interfaceList.put("Login", new LoginGUI());
		interfaceList.put("Admin", new AdminGUI());
		interfaceList.put("Employee", new EmployeeGUI());
	}

	/**
	 * @param key name of the GUI to switch to.
	 */
	public void SetView(String key) {
		frame.getContentPane().removeAll();
		frame.add(interfaceList.get(key).getCanvas());
		frame.pack();
	}
}
