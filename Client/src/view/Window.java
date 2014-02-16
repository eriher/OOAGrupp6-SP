/**
 * Contains all the different GUIs' and also manage the frame itself.
 * 
 * @author David Stromner, Benjamin Wijk, Magnus Kallten
 * @version 2013-02-16
 */

package view;

import java.awt.Frame;
import java.util.HashMap;
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
		// TODO Recive a char/int/string that identifies the user level instead
		// of Boolean.
		if (o instanceof Communication && arg instanceof Boolean) {
			if ((Boolean) arg == true) {
				setView("Employee");
			}
			// Bad things happened, display error.
			else {
				setErrorMessage("Bad login information");
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
		frame.setExtendedState(Frame.MAXIMIZED_BOTH); // Needs to be called
		// after add because of
		// internal layouts
	}
}
