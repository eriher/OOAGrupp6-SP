/**
 * Contains all the different GUIs' and dialogs for the program, also contains the key-frame.
 * 
 * @author David Stromner
 * @version 2013-02-22
 */

package view;

import java.awt.Frame;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Communication;
import view.dialog.CustomDialog;
import view.gui.GUI;
import controller.Workflow;

public class Window extends JFrame implements Observer {
	private static final long serialVersionUID = 7991892771427260131L;
	private HashMap<String, GUI> interfaceList;
	private HashMap<String, CustomDialog> dialogList;

	public Window() {
		addObserver(Workflow.getInstance().getCommunication());
		interfaceList = new HashMap<String, GUI>();
		dialogList = new HashMap<String, CustomDialog>();
		createFrame();
	}

	/**
	 * Let other objects tell that we want to be notified about updates.
	 * 
	 * @param o
	 *            the object that the class want updates from.
	 */
	public void addObserver(Observable o) {
		o.addObserver(this);
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Communication) {
			// If it's from communication it's always a linkedlist<object>.
			LinkedList<Object> argsList = (LinkedList<Object>) arg;

			// Check what type of message was received
			if (((String) argsList.get(0)).compareToIgnoreCase("Login") == 0) {
				// Check which type of user it was
				if (((String) argsList.get(1)).compareToIgnoreCase("Employee") == 0) {
					setView("EmployeeGUI");
				} else if (((String) argsList.get(1))
						.compareToIgnoreCase("Admin") == 0) {
					setView("AdminGUI");
				} else if (((String) argsList.get(1))
						.compareToIgnoreCase("False") == 0) {
					setErrorMessage("Bad login information");
					Workflow.getInstance().getCommunication().disconnect();
				}
				// Bad/Unexpected things happened, display error.
				else {
					setErrorMessage("Critical error!\n\n" + arg.toString());
					Workflow.getInstance().getCommunication().disconnect();
				}
			}
		}
	}

	/**
	 * Set an error message to be displayed in a new dialog.
	 * 
	 * @param msg
	 *            message to be displayed in the pop up.
	 */
	public void setErrorMessage(String msg) {
		JOptionPane.showMessageDialog(this, msg, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * createViews(); Initiate main window.
	 */
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("MarximumWorker 9001");
	}

	/**
	 * Create the view that 'key' contains. Removes old view.
	 * 
	 * @param key
	 *            name of the GUI to switch to. Must be identic to the class
	 *            name.
	 */
	public void setView(String key) {
		String s = "view.gui." + key;
		try {
			GUI c = (GUI) Class.forName(s).newInstance();
			interfaceList.put(key, c);
			// So many exceptions
		} catch (Exception e) {
			e.printStackTrace();
		}

		getContentPane().removeAll();
		add(interfaceList.get(key).getCanvas());
		pack();
		setMinimumSize(getPreferredSize());
		setExtendedState(Frame.MAXIMIZED_BOTH); // Needs to be called
		// after add because of
		// internal layouts
	}

	/**
	 * Create and return the requested dialog. Removes any old dialog.
	 * 
	 * @param key
	 *            name of the dialog window. Must be identic to the class name.
	 * @return instance of the dialog, null otherwise.
	 */
	public JDialog getDialog(String key) {
		try {
			String s = "view.dialog." + key;
			CustomDialog c = (CustomDialog) Class.forName(s)
					.getConstructor(Communication.class)
					.newInstance(Workflow.getInstance().getCommunication());
			dialogList.put(key, c);
			// So many exceptions
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dialogList.get(key);
	}
}
