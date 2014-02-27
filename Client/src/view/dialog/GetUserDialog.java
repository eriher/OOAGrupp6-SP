/**
 * Dialog for fetching a user from the server and displaying all its information.
 * 
 * @author David Stromner
 * @version 2014-02-27
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Communication;
import model.User;
import controller.ActionHandler;

public class GetUserDialog extends CustomDialog implements Observer {
	private static final long serialVersionUID = 5847075780992545520L;

	public GetUserDialog(Communication communication) {
		super();

		communication.addObserver(this);
		communication.send("GetAllUsers");
		setTitle("Get user");
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Communication) {
			LinkedList<Object> argsList = (LinkedList<Object>) arg;

			// Check what type of message was received
			if (((String) argsList.get(0)).compareToIgnoreCase("GetAllUsers") == 0) {
				argsList.removeFirst();
				// TODO STOP LOOPING!
				for (Object s : argsList) {
					((JComboBox) components.get("comboBox")).addItem(s);
				}
			} else if (((String) argsList.get(0))
					.compareToIgnoreCase("GetUser") == 0) {
				User user = (User) argsList.get(1);
			}
		}
	}

	/**
	 * Create all the components that is going to be used inside the dialog.
	 */
	@Override
	protected void create() {
		super.create();
		Container temp;

		// FilterLabel
		temp = new JLabel("Filter: ");
		components.put("filterLabel", temp);

		// TextField
		temp = new JTextField();
		components.put("text", temp);

		// ComboBox
		temp = new JComboBox<String>();
		components.put("comboBox", temp);

		// An OKButton needs to be created in a subclass since each OKButton is
		// going to trigger different things.
		temp = new JButton("Ok");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().getUserDialogOk(customDialog,
						components.get("comboBox"));
			}
		});
		components.put("okButton", temp);
	}

	/**
	 * Place all the created components.
	 */
	@Override
	protected void build() {
		super.build();
		GridBagConstraints c;
		JPanel canvas = getCanvas();

		// FilterLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("filterLabel"), c);

		// TextField
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("text"), c);

		// ComboBox
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("comboBox"), c);
	}
}