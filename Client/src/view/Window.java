/**
 * Contains all the different GUIs' and also manage the frame itself.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-12
 */

package view;

import java.awt.Frame;
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
	}

	/**
	 * Initiate main window
	 */
	private void createFrame() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * Create all GUIs
	 */
	private void initViews() {
		interfaceList.put("Login", new LoginGUI());
		interfaceList.put("Admin", new AdminGUI());
		interfaceList.put("Employee", new EmployeeGUI());
	}

	/**
	 * @param key
	 *            name of the GUI to switch to.
	 */
	public void setView(String key) throws IllegalArgumentException {
		if (interfaceList.get(key) == null) {
			throw new IllegalArgumentException("Invalid key: " + key);
		}

		frame.getContentPane().removeAll();
		frame.add(interfaceList.get(key).getCanvas());
		frame.pack();
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); // Needs to be called
		// after add because of
		// internal layouts
	}
}
