/**
 * @author David Stromner
 * @version 2014-02-22
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.FileManagement;

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
		
		temp = new JPanel();
		temp.setLayout(new GridBagLayout());
		components.put("wrapperPanel", temp);

		temp = new JLabel("IP:");
		components.put("ipLabel", temp);

		temp = new JLabel("Port:");
		components.put("portLabel", temp);
		
		String s =FileManagement.getInstance().readLine("config.txt");
		String sArr[] = s.split(":");
		
		temp = new JTextField();
		components.put("ipText", temp);
		((JTextField)temp).setText(sArr[0]);

		temp = new JTextField();
		components.put("portText", temp);
		((JTextField)temp).setText(sArr[1]);
	}

	/**
	 * Place all created components.
	 */
	@Override
	protected void build() {
		super.build();
		GridBagConstraints c = new GridBagConstraints();
		JPanel canvas = getCanvas();
		Container temp;
		
		// IPLabel
		temp = components.get("ipLabel");
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 0, 10);
		canvas.add(temp, c);
		
		// PortLabel
		temp = components.get("portLabel");
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(5, 0, 0, 10);
		canvas.add(temp, c);
		
		// IPText
		temp = components.get("ipText");
		c.gridx = 1;
		c.gridy = 0;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 40;
		canvas.add(temp, c);
		
		// PortText
		temp = components.get("portText");
		c.gridx = 1;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 40;
		canvas.add(temp, c);
		
		// WrapperPanel
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 215;
		c.ipady = 125;
		c.fill = GridBagConstraints.NONE;
		components.get("wrapperPanel").add(canvas, c);
		
		add(components.get("wrapperPanel"));
	}
}
