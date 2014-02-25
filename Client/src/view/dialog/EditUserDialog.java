/**
 * Dialog for editing a user in the admin GUI. Receives a list from the server on all the users which can be filterted.
 * 
 * @author David Stromner
 * @version 2014-02-25
 */

package view.dialog;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.ActionHandler;

public class EditUserDialog extends CustomDialog {
	private static final long serialVersionUID = -274172269787570689L;

	public EditUserDialog() {
		super();

		setTitle("Edit User");
	}

	/**
	 * Create all components that is going to be used inside the dialog.
	 */
	@Override
	protected void create() {
		super.create();
		Container temp;

		initEditUser();
		initGetUser();
		initOriginalInfo();

		// An OKButton needs to be created in a subclass to CustomDialog since
		// each OKButton is going to trigger different things.
		// OkButton
		temp = new JButton("Ok");
		((JButton) temp).addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActionHandler.getInstance().createUserDialogOk(customDialog,
						components.get("usernameText"),
						components.get("passwordText"),
						components.get("authorityText"));
			}
		});
		components.put("okButton", temp);
	}

	/**
	 * Create all components that is going to be used inside the edit dialog.
	 */
	private void initEditUser() {
		Container temp;

		// Panel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridBagLayout());
		components.put("eUPanel", temp);

		// Label
		temp = new JLabel("Edit user:");
		components.put("eULabel", temp);

		// PasswordLabel
		temp = new JLabel("Password:");
		components.put("eUPasswordLabel", temp);

		// AuthorityLabel
		temp = new JLabel("Authority Level:");
		components.put("eUAuthorityLabel", temp);

		// PasswordText
		temp = new JTextField();
		components.put("eUPasswordText", temp);

		// AuthorityComboBox
		String[] sArr = { "Employee", "Admin" };
		temp = new JComboBox<String>(sArr);
		components.put("eUAuthorityComboBox", temp);

		// DelteUserButton
		temp = new JButton("Delete user");
		components.put("eUDeleteUserButton", temp);
	}

	/**
	 * Create all components that is going to be used inside the get dialog.
	 */
	private void initGetUser() {
		Container temp;

		// Panel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridBagLayout());
		components.put("gUPanel", temp);

		// Label
		temp = new JLabel("Get user:");
		components.put("gULabel", temp);

		// TextField
		temp = new JTextField();
		components.put("gUText", temp);

		// ComboBox
		temp = new JComboBox<String>();
		components.put("gUComboBox", temp);

		// Button
		temp = new JButton("Fetch user");
		components.put("fetchUserButton", temp);
	}

	/**
	 * Create all components that is going to be used inside the original info
	 * dialog.
	 */
	private void initOriginalInfo() {
		Container temp;

		// Panel
		temp = new JPanel();
		((JPanel) temp).setLayout(new GridBagLayout());
		components.put("oIPanel", temp);

		// Label
		temp = new JLabel("Original info:");
		components.put("oILabel", temp);

		// UsernameLabel
		temp = new JLabel("Username:");
		components.put("oIUsernameLabel", temp);

		// PasswordLabel
		temp = new JLabel("Password:");
		components.put("oIPasswordLabel", temp);

		// AuthorityLabel
		temp = new JLabel("Authority Level:");
		components.put("oIAuthorityLabel", temp);

		// UsernameText
		temp = new JTextField();
		((JTextField) temp).setEditable(false);
		((JTextField) temp).setEnabled(false);
		components.put("oIUsernameText", temp);

		// PasswordText
		temp = new JTextField();
		((JTextField) temp).setEditable(false);
		((JTextField) temp).setEnabled(false);
		components.put("oIPasswordText", temp);

		// AuthorityText
		temp = new JTextField();
		((JTextField) temp).setEditable(false);
		((JTextField) temp).setEnabled(false);
		components.put("oIAuthorityText", temp);
	}

	/**
	 * Place all created components.
	 */
	@Override
	protected void build() {
		super.build();

		buildEditUser();
		buildGetUser();
		buildOriginalInfo();
	}

	/**
	 * Place all edit components.
	 */
	private void buildEditUser() {
		GridBagConstraints c;
		JPanel canvas = getCanvas();
		JPanel editUser = (JPanel) components.get("eUPanel");

		// Panel
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridheight = 2;
		c.insets = new Insets(0, 0, 0, 15);
		canvas.add(editUser, c);

		// Label
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 30, 0);
		editUser.add(components.get("eULabel"), c);

		// PasswordLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		editUser.add(components.get("eUPasswordLabel"), c);

		// AuthorityLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		editUser.add(components.get("eUAuthorityLabel"), c);

		// PasswordText
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		editUser.add(components.get("eUPasswordText"), c);

		// AuthorityComboBox
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		editUser.add(components.get("eUAuthorityComboBox"), c);

		// DeleteUserButton
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 4;
		c.weighty = 1;
		c.insets = new Insets(50, 0, 0, 0);
		editUser.add(components.get("eUDeleteUserButton"), c);

	}

	/**
	 * Place all get components.
	 */
	private void buildGetUser() {
		GridBagConstraints c;
		JPanel canvas = getCanvas();
		JPanel getUser = (JPanel) components.get("gUPanel");

		// Panel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 0;
		canvas.add(getUser, c);

		// Label
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 30, 0);
		c.anchor = GridBagConstraints.WEST;
		getUser.add(components.get("gULabel"), c);

		// TextField
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 65;
		c.insets = new Insets(0, 0, 0, 10);
		c.fill = GridBagConstraints.HORIZONTAL;
		getUser.add(components.get("gUText"), c);

		// ComboBox
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 65;
		c.fill = GridBagConstraints.HORIZONTAL;
		getUser.add(components.get("gUComboBox"), c);

		// Button
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.weighty = 1;
		c.insets = new Insets(50, 0, 0, 0);
		getUser.add(components.get("fetchUserButton"), c);

	}

	/**
	 * Place all original info components.
	 */
	private void buildOriginalInfo() {
		GridBagConstraints c;
		JPanel canvas = getCanvas();
		JPanel original = (JPanel) components.get("oIPanel");

		// Panel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		canvas.add(original, c);

		// Label
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0, 0, 30, 0);
		c.anchor = GridBagConstraints.WEST;
		original.add(components.get("oILabel"), c);

		// UsernameLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 1;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		original.add(components.get("oIUsernameLabel"), c);

		// PasswordLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 2;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(2, 0, 2, 10);
		original.add(components.get("oIPasswordLabel"), c);

		// AuthorityLabel
		c = new GridBagConstraints();
		c.gridx = 1;
		c.gridy = 3;
		c.anchor = GridBagConstraints.EAST;
		c.insets = new Insets(0, 0, 0, 10);
		original.add(components.get("oIAuthorityLabel"), c);

		// UsernameText
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 1;
		c.ipadx = 100;
		c.fill = GridBagConstraints.HORIZONTAL;
		original.add(components.get("oIUsernameText"), c);

		// PasswordText
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.ipadx = 100;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2, 0, 2, 0);
		original.add(components.get("oIPasswordText"), c);

		// AuthorityText
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 3;
		c.ipadx = 100;
		c.fill = GridBagConstraints.HORIZONTAL;
		original.add(components.get("oIAuthorityText"), c);
	}

}
