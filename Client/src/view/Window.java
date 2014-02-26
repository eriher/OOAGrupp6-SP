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
import view.dialog.ChangePasswordDialog;
import view.dialog.CreateUserDialog;
import view.dialog.EditUserDialog;
import view.dialog.NetworkDialog;
import controller.Workflow;

public class Window extends JFrame implements Observer {
	private static final long serialVersionUID = 7991892771427260131L;
	private HashMap<String, GUI> interfaceList;
	private HashMap<String, JDialog> dialogList;

	public Window() {
		interfaceList = new HashMap<String, GUI>();
		dialogList = new HashMap<String, JDialog>();
		createFrame();
		createViews();
		createDialogs();

		new NetworkDialog();
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
			LinkedList<Object> argsList = (LinkedList<Object>) arg;
			
			// Check what type of message was received
			if (((String) argsList.get(0)).compareToIgnoreCase("Login") == 0) {
				// Check which type of user it was
				if (((String) argsList.get(1)).compareToIgnoreCase("Employee") == 0) {
					setView("Employee");
				} else if (((String) argsList.get(1))
						.compareToIgnoreCase("Admin") == 0) {
					setView("Admin");
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
	 * Initiate main window.
	 */
	private void createFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setTitle("MarximumWorker 9001");
	}

	/**
	 * Create all the GUIs.
	 */
	private void createViews() {
		interfaceList.put("Login", new LoginGUI());
		interfaceList.put("Admin", new AdminGUI());
		interfaceList.put("Employee", new EmployeeGUI());
	}

	/**
	 * Create all the dialogs.
	 */
	private void createDialogs() {
		dialogList.put("networkDialog", new NetworkDialog());
		dialogList.put("createUserDialog", new CreateUserDialog());
		dialogList.put("editUserDialog", new EditUserDialog());
		dialogList.put("changePasswordDialog", new ChangePasswordDialog());
	}

	/**
	 * Switch the view that currently is seen. Removes all current components from frame and replaces them with new ones.
	 * 
	 * @param key
	 *            name of the GUI to switch to.
	 * @throws IllegalArgumentException
	 *             if 'key' contains a non-existing key.
	 */
	public void setView(String key) throws IllegalArgumentException {
		if (interfaceList.get(key) == null) {
			throw new IllegalArgumentException("Invalid key: " + key);
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
	 * Return the dialog that's requested.
	 * 
	 * @param key
	 *            name of the dialog window.
	 * @return instance of the dialog, null otherwise.
	 */
	public JDialog getDialog(String key) {
		return dialogList.get(key);
	}
}
