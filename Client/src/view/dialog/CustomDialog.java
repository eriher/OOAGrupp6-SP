/**
 * Base class for all custom dialogs, contains an empty Ok-button and an Cancel-button.
 * 
 * @author David Stromner
 * @version 2014-02-24
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import controller.ActionHandler;

public class CustomDialog extends JDialog {
	private static final long serialVersionUID = 4131677888057562310L;
	private JPanel canvas;
	protected final CustomDialog customDialog = this;
	protected HashMap<String, Container> components;

	public CustomDialog() {
		super();
		components = new HashMap<String, Container>();

		create();
		build();

		pack();
		setTitle("Custom Dialog");
		setModal(true); // Block all other components when visible
		setResizable(false);
	}

	/**
	 * @return panel to put all components inside.
	 */
	public JPanel getCanvas() {
		return canvas;
	}

	/**
	 * Create all components that is going to be used inside the dialog.
	 */
	protected void create() {
		canvas = new JPanel();
		canvas.setLayout(new GridBagLayout());
		Container temp;

		// WrapperPanel
		temp = new JPanel();
		temp.setLayout(new GridBagLayout());
		components.put("wrapperPanel", temp);

		// CancelButton
		temp = new JButton("Cancel");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().dialogCancel(customDialog);
			}
		});
		components.put("cancelButton", temp);

		// An OKButton needs to be created in a subclass since each OKButton is
		// going to trigger different things.
	}

	/**
	 * Place all created components.
	 */
	protected void build() {
		GridBagConstraints c;

		// Canvas
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.ipadx = 215;
		c.ipady = 125;
		components.get("wrapperPanel").add(canvas, c);

		// CancelButton
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.insets = new Insets(0, 10, 10, 10);
		components.get("wrapperPanel").add(components.get("cancelButton"), c);

		// OkButton
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.SOUTHEAST;
		c.insets = new Insets(0, 0, 10, 0);
		components.get("wrapperPanel").add(components.get("okButton"), c);

		add(components.get("wrapperPanel"));
	}
}
