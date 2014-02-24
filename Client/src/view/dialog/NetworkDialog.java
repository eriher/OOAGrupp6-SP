/**
 * @author David Stromner
 * @version 2014-02-24
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.FileManagement;
import controller.ActionHandler;

public class NetworkDialog extends CustomDialog {
	private static final long serialVersionUID = 1894512864549284805L;

	public NetworkDialog() {
		super();

		setTitle("Network Dialog");
	}

	/**
	 * Create all components that is going to be used inside the dialog.
	 */
	@Override
	protected void create() {
		super.create();
		Container temp;

		// InfoLabel
		temp = new JLabel("Restart the program for changes to take effect");
		components.put("infoLabel", temp);

		// IPLabel
		temp = new JLabel("IP:");
		components.put("ipLabel", temp);

		// PortLabel
		temp = new JLabel("Port:");
		components.put("portLabel", temp);

		String s = FileManagement.getInstance().readLine("config.txt");
		String sArr[] = s.split(":");

		// IPText
		temp = new JTextField();
		((JTextField) temp).setText(sArr[0]);
		components.put("ipText", temp);

		// PortText
		temp = new JTextField();
		((JTextField) temp).setText(sArr[1]);
		components.put("portText", temp);

		// An OKButton needs to be created in a subclass to CustomDialog since
		// each OKButton is going to trigger different things.
		// OkButton
		temp = new JButton("Ok");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().networkDialogOk(customDialog,
						components.get("ipText"), components.get("portText"));
			}
		});
		components.put("okButton", temp);
	}

	/**
	 * Place all created components.
	 */
	@Override
	protected void build() {
		super.build();
		GridBagConstraints c;
		JPanel canvas = getCanvas();

		// InfoLabel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 4;
		c.insets = new Insets(0, 0, 20, 0);
		canvas.add(components.get("infoLabel"), c);

		// IPLabel
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("ipLabel"), c);

		// PortLabel
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(components.get("portLabel"), c);

		// IPText
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 1;
		c.ipadx = 40;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("ipText"), c);

		// PortText
		c = new GridBagConstraints();
		c.gridx = 3;
		c.gridy = 2;
		c.ipadx = 40;
		c.fill = GridBagConstraints.HORIZONTAL;
		canvas.add(components.get("portText"), c);
	}
}