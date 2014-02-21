/**
 * Contains all the different GUIs' and also manage the frame itself.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-16
 */

package view;

import java.awt.Frame;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Communication;

public class Window implements Observer {
	private HashMap<String, GUI> interfaceList;
	private JFrame frame;

	public Window() {
		interfaceList = new HashMap<String, GUI>();
		createFrame();
		// Init all the views
		initViews();

		JOptionPane.showInputDialog(frame, "Info about this popup",
				"Network config", JOptionPane.PLAIN_MESSAGE, null, null, "ham");
	}

	/**
	 * Sign up to be notified about updates from object 'o'.
	 * 
	 * @param o
	 *            The object that the class want updates from.
	 */
	public void addObserver(Observable o) {
		o.addObserver(this);
	}

	public void update(Observable o, Object arg) {
		@SuppressWarnings("unchecked")
		LinkedList<Object> argsList = (LinkedList<Object>) arg;

		if (o instanceof Communication) {
			if (((String) argsList.get(0)).compareToIgnoreCase("Login") == 0) {
				if (((String) argsList.get(1)).compareToIgnoreCase("Employee") == 0) {
					setView("Employee");
				} else if (((String) argsList.get(1))
						.compareToIgnoreCase("Admin") == 0) {
					setView("Admin");
				} else if (((String) argsList.get(1))
						.compareToIgnoreCase("False") == 0) {
					setErrorMessage("Bad login information");
				}
				// Bad/Unexpected things happened, display error.
				else {
					setErrorMessage("Critical error!\n\n" + arg.toString());
				}
			}
		}
	}

	/**
	 * @param msg
	 *            Message to be displayed in the pop up
	 */
	public void setErrorMessage(String msg) {
		JOptionPane.showMessageDialog(frame, msg, "Error",
				JOptionPane.ERROR_MESSAGE);
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
	 * @throws IllegalArgumentException
	 *             If 'key' contains a non-existing key.
	 */
	public void setView(String key) throws IllegalArgumentException {
		if (interfaceList.get(key) == null) {
			throw new IllegalArgumentException("Invalid key: " + key);
		}

		frame.getContentPane().removeAll();
		frame.add(interfaceList.get(key).getCanvas());
		frame.pack();
		frame.setMinimumSize(frame.getPreferredSize());
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); // Needs to be called
		// after add because of
		// internal layouts
	}
}
