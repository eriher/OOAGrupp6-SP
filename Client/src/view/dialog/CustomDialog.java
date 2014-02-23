package view.dialog;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class CustomDialog extends JDialog {
	private static final long serialVersionUID = 4131677888057562310L;
	private JPanel canvas;
	protected final CustomDialog customDialog = this;
	protected HashMap<String, Container> components;
	
	public CustomDialog(){
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
	public JPanel getCanvas(){
		return canvas;
	}
	
	/**
	 * Create all components that is going to be used inside the dialog.
	 */
	protected void create(){
		canvas = new JPanel();
		canvas.setLayout(new GridBagLayout());
	}
	
	/**
	 * Place all created components.
	 */
	protected void build(){
		
	}
}
